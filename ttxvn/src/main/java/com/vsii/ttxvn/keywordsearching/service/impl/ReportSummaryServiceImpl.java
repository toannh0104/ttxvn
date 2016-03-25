package com.vsii.ttxvn.keywordsearching.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import com.vsii.ttxvn.keywordsearching.domain.ReportCriteria;
import com.vsii.ttxvn.keywordsearching.domain.ReportItem;
import com.vsii.ttxvn.keywordsearching.domain.SolrDoc;
import com.vsii.ttxvn.keywordsearching.domain.SolrResponse;
import com.vsii.ttxvn.keywordsearching.domain.TtxvnPageRequest;
import com.vsii.ttxvn.keywordsearching.entity.Category;
import com.vsii.ttxvn.keywordsearching.entity.CategoryKeyword;
import com.vsii.ttxvn.keywordsearching.entity.Keyword;
import com.vsii.ttxvn.keywordsearching.entity.SourceUrl;
import com.vsii.ttxvn.keywordsearching.enums.LanguageCode;
import com.vsii.ttxvn.keywordsearching.httpclient.solr.SolrOxmClient;
import com.vsii.ttxvn.keywordsearching.service.CategoryService;
import com.vsii.ttxvn.keywordsearching.service.KeywordService;
import com.vsii.ttxvn.keywordsearching.service.ReportSummaryService;
import com.vsii.ttxvn.keywordsearching.utils.TtxvnCache;
import com.vsii.ttxvn.keywordsearching.utils.DateUtils;
import com.vsii.ttxvn.keywordsearching.utils.LanguageUtils;
import com.vsii.ttxvn.keywordsearching.utils.TtxvnCache;
import com.vsii.ttxvn.keywordsearching.utils.TtxvnCache.CacheObject;

/**
 * <p>
 * An implementation of {@code SearchNewsService}.
 * </p>
 * 
 * @version 1.0
 * @author bachtv
 **/
@Service("reportSummaryService")
public class ReportSummaryServiceImpl implements ReportSummaryService {
    @Autowired
    SolrOxmClient<SolrDoc> solrOxmClient;

    @Autowired
    KeywordService keywordService;

    @Autowired
    CategoryService categoryService;
    
    
    private List<Category> listCategory;
    private List<CategoryKeyword> listCategoryKeyword ;
    private List<Keyword> listKeyword ;
    private List<String> listKeywordNameDistinct;
    private List<SourceUrl> listSourceUrl;
    private Map mapMetadataById = new HashMap<String, String>();
    private Map mapKeywordNameByNumber = new HashMap<String, String>();
    
    private static List<ReportItem> result;
    private static long totalElements = 0;

    private TtxvnCache cacheUtils=new TtxvnCache();
    
    /**
     * {@inheritDoc}
     * 
     * @see com.vsii.ttxvn.keywordsearching.service.SearchNewsService#findNewspaper(com.vsii.ttxvn.keywordsearching.domain.SearchCriteria,
     *      com.vsii.ttxvn.keywordsearching.domain.TtxvnPageRequest)
     */
    
    @Override
    public void init(Map map) {
        listCategory = categoryService.findAll(Category.class);
        listKeyword = keywordService.findAll(Keyword.class);
        listKeywordNameDistinct = keywordService.getDistinctKeyword();
        listCategoryKeyword = keywordService.findAll(CategoryKeyword.class);
        listSourceUrl = keywordService.findAll(SourceUrl.class);
        mapKeywordNameByNumber = map;
                
        for (Category category : listCategory) {
            mapMetadataById.put(Category.class.getName() + "-" + category.getId(), category);
        }
        for (Keyword keyword : listKeyword) {
            mapMetadataById.put(Keyword.class.getName() + "-" + keyword.getId(), keyword);
        }

        for (SourceUrl sourceUrl : listSourceUrl) {
            mapMetadataById.put(SourceUrl.class.getName() + "-" + sourceUrl.getId(), sourceUrl);
        }
                
    }
    
    @Override
    public PageImpl<ReportItem> queryReport(ReportCriteria rootCriteria, TtxvnPageRequest pageRequest)
    {     
        result = new ArrayList<ReportItem>();
        boolean isNotResult=true;
        /*
         * Use cache to  load Result
         */
        if(cacheUtils.getCache(cacheKey(rootCriteria))!=null){
        	
        	isNotResult=false;
        	result=(List<ReportItem>) cacheUtils.getCache(cacheKey(rootCriteria)).getValue();
        	if(result==null){
        		isNotResult=true;
        	}
        }
        /*
         * if cache don't exit or cache deleted. Search result by solr query
         * and save new result to cache.
         */
        if(isNotResult) {
			result = searchReportItems(rootCriteria,
					pageRequest);
			CacheObject co = new CacheObject(result, cacheKey(rootCriteria), 5);
			cacheUtils.putCache(co);

		}
        
        List<ReportItem> pageResult = new ArrayList<ReportItem>();
        int maxSize = (int)(pageRequest.getFirstElement()+pageRequest.getPageSize());
        if (maxSize > result.size()) maxSize = result.size();
        for (int i=(int) pageRequest.getFirstElement(); i<maxSize ; i++) {
            pageResult.add(result.get(i));
        }
        
        return new PageImpl<ReportItem>(pageResult, pageRequest, result.size());
    }
    
    
    public List<ReportItem> getListReportItem() {
        return ReportSummaryServiceImpl.result;
    }
    
    public List<ReportItem> searchReportItems(ReportCriteria rootCriteria,TtxvnPageRequest pageRequest){
    	 result = new ArrayList<ReportItem>();
    	 if (!rootCriteria.getKeyword().equals("0") && !rootCriteria.getSourceUrl().equals("0")){
             ReportCriteria subCriteria = new ReportCriteria(rootCriteria.getCategory(), (String) mapKeywordNameByNumber.get(rootCriteria.getKeyword()) , ((SourceUrl) mapMetadataById.get(SourceUrl.class.getName()+"-"+rootCriteria.getSourceUrl())).getUrl(), rootCriteria.getStartDate(), rootCriteria.getEndDate(), rootCriteria.getPage());
             subCriteria.setLanguageCode(LanguageUtils.getCurrentLanguageCode());
             result.addAll(buildReportItem(pageRequest, subCriteria, listCategoryKeyword));  
         } 
         
         if (!rootCriteria.getKeyword().equals("0") && rootCriteria.getSourceUrl().equals("0")) {
             for (SourceUrl sourceUrl : listSourceUrl) {
                 ReportCriteria subCriteria = new ReportCriteria(rootCriteria.getCategory(), (String) mapKeywordNameByNumber.get(rootCriteria.getKeyword()) , sourceUrl.getUrl(), rootCriteria.getStartDate(), rootCriteria.getEndDate(), rootCriteria.getPage());
                 subCriteria.setLanguageCode(LanguageUtils.getCurrentLanguageCode());
                 result.addAll(buildReportItem(pageRequest, subCriteria, listCategoryKeyword));  
             }
         } 

         if (rootCriteria.getKeyword().equals("0") && !rootCriteria.getSourceUrl().equals("0")) {
             for (String keyword : listKeywordNameDistinct) {
                 ReportCriteria subCriteria = new ReportCriteria(rootCriteria.getCategory(), keyword , ((SourceUrl) mapMetadataById.get(SourceUrl.class.getName()+"-"+rootCriteria.getSourceUrl())).getUrl(), rootCriteria.getStartDate(), rootCriteria.getEndDate(), rootCriteria.getPage());
                 subCriteria.setLanguageCode(LanguageUtils.getCurrentLanguageCode());
                 result.addAll(buildReportItem(pageRequest, subCriteria, listCategoryKeyword));
             }
         } 

         if (rootCriteria.getKeyword().equals("0") && rootCriteria.getSourceUrl().equals("0")) {
             for (String keyword : listKeywordNameDistinct) {
                 for (SourceUrl sourceUrl : listSourceUrl) {
                     ReportCriteria subCriteria = new ReportCriteria(rootCriteria.getCategory(), keyword , sourceUrl.getUrl(), rootCriteria.getStartDate(), rootCriteria.getEndDate(), rootCriteria.getPage());
                     subCriteria.setLanguageCode(LanguageUtils.getCurrentLanguageCode());
                     result.addAll(buildReportItem(pageRequest, subCriteria, listCategoryKeyword));
                 }
             }
         } 
         
    	return result;
    }

    private List<ReportItem> buildReportItem(TtxvnPageRequest pageRequest, ReportCriteria subCriteria, List<CategoryKeyword> listCategoryKeyword) {
        List<ReportItem> subResult = new ArrayList<ReportItem>();
        final SolrResponse solrResponse = solrOxmClient.searchForReporting(subCriteria, pageRequest.getFirstElement(), pageRequest.getPageSize());
        List<SolrDoc> solrDocs = new ArrayList<SolrDoc>();          
        if (solrResponse != null)
        {
            totalElements = totalElements + solrResponse.getNumFound();
            solrDocs = solrResponse.getSolrDocs();          
            
            if (solrDocs.size()>0) {
                for (CategoryKeyword categoryKeyword : listCategoryKeyword) {
                    if ( 
                        ((Keyword) mapMetadataById.get(Keyword.class.getName()+"-"+categoryKeyword.getKeywordId())).getName().equals(subCriteria.getKeyword()) 
                        && (( subCriteria.getCategory().equals("0")) || (!subCriteria.getCategory().equals("0") && ((Category) mapMetadataById.get(Category.class.getName()+"-"+subCriteria.getCategory())).getName().equals(categoryKeyword.getCategoryName())) )
                    ) {
                        Keyword keyword = (Keyword) mapMetadataById.get(Keyword.class.getName() + "-" + categoryKeyword.getKeywordId());                        
                        subResult.add(new ReportItem(subCriteria.getKeyword(), categoryKeyword.getCategoryName(), subCriteria.getSourceUrl(), DateUtils.format(keyword.getStartDate(), "dd-MM-yyyy"), DateUtils.format(keyword.getEndDate(), "dd-MM-yyyy"), solrDocs.size(), calculateNumOfAppearance(solrDocs, subCriteria)));
                    }
                }
            }
        }       
        return subResult;
    }

    private int calculateNumOfAppearance(List<SolrDoc> solrDocs, ReportCriteria subCriteria) {
        int result = 0;
        for (SolrDoc solrDoc : solrDocs) {
            try {
                result = result + org.springframework.util.StringUtils.countOccurrencesOf(solrDoc.getContent().toLowerCase(), subCriteria.getKeyword().toLowerCase());
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    @SuppressWarnings("unchecked")
    @Override
    public List<SolrDoc> report( Date from, Date to, LanguageCode langCode) {
        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put(SolrOxmClient.QUERY_PARAM, "lang-code:\""
                + langCode.getCode().toLowerCase() + "\"");
        StringBuilder dateRange = new StringBuilder("tstamp:[");
        String date = "*";
        if (from != null)
            date = DateUtils.getISODate(from);
        dateRange.append(date).append(" TO ");
        date = "*";
        if (to != null)
            date = DateUtils.getISODate(to);
        dateRange.append(date).append("]");
        queryParams.put(SolrOxmClient.QUERY_FILTER, dateRange.toString());
        queryParams.put(SolrOxmClient.QUERY_RESPONSE_WRITER, SolrOxmClient.JSON_RESPONSE);
        queryParams.put(SolrOxmClient.QUERY_FIELD_LIST, "url title");
        queryParams.put(SolrOxmClient.QUERY_SORT, "tstamp desc");
        queryParams.put(SolrOxmClient.QUERY_MAX_ROW_RETURN, String.valueOf(200));
        SolrResponse response = solrOxmClient.searchBySolr(queryParams, langCode.getCode().toUpperCase());
        if (response == null)
            return Collections.EMPTY_LIST;
        return response.getSolrDocs();
    }

    
    /**
     * <p>
     * Getter for listCategory.
     * </p>
     * 
     * @return the listCategory
     */
    public List<Category> getListCategory() {
        return listCategory;
    }

    /**
     * <p>
     * Setting value for listCategory.
     * </p>
     * 
     * @param listCategory the listCategory to set
     */
    public void setListCategory(List<Category> listCategory) {
        this.listCategory = listCategory;
    }

    /**
     * <p>
     * Getter for listCategoryKeyword.
     * </p>
     * 
     * @return the listCategoryKeyword
     */
    public List<CategoryKeyword> getListCategoryKeyword() {
        return listCategoryKeyword;
    }

    /**
     * <p>
     * Setting value for listCategoryKeyword.
     * </p>
     * 
     * @param listCategoryKeyword the listCategoryKeyword to set
     */
    public void setListCategoryKeyword(List<CategoryKeyword> listCategoryKeyword) {
        this.listCategoryKeyword = listCategoryKeyword;
    }

    /**
     * <p>
     * Getter for listKeyword.
     * </p>
     * 
     * @return the listKeyword
     */
    public List<Keyword> getListKeyword() {
        return listKeyword;
    }

    /**
     * <p>
     * Setting value for listKeyword.
     * </p>
     * 
     * @param listKeyword the listKeyword to set
     */
    public void setListKeyword(List<Keyword> listKeyword) {
        this.listKeyword = listKeyword;
    }

    /**
     * <p>
     * Getter for listKeywordNameDistinct.
     * </p>
     * 
     * @return the listKeywordNameDistinct
     */
    public List<String> getListKeywordNameDistinct() {
        return listKeywordNameDistinct;
    }

    /**
     * <p>
     * Setting value for listKeywordNameDistinct.
     * </p>
     * 
     * @param listKeywordNameDistinct the listKeywordNameDistinct to set
     */
    public void setListKeywordNameDistinct(List<String> listKeywordNameDistinct) {
        this.listKeywordNameDistinct = listKeywordNameDistinct;
    }

    /**
     * <p>
     * Getter for listSourceUrl.
     * </p>
     * 
     * @return the listSourceUrl
     */
    public List<SourceUrl> getListSourceUrl() {
        return listSourceUrl;
    }

    /**
     * <p>
     * Setting value for listSourceUrl.
     * </p>
     * 
     * @param listSourceUrl the listSourceUrl to set
     */
    public void setListSourceUrl(List<SourceUrl> listSourceUrl) {
        this.listSourceUrl = listSourceUrl;
    }

    /**
     * <p>
     * Getter for mapMetadataById.
     * </p>
     * 
     * @return the mapMetadataById
     */
    public Map getMapMetadataById() {
        return mapMetadataById;
    }

    /**
     * <p>
     * Setting value for mapMetadataById.
     * </p>
     * 
     * @param mapMetadataById the mapMetadataById to set
     */
    public void setMapMetadataById(Map mapMetadataById) {
        this.mapMetadataById = mapMetadataById;
    }

    /**
     * <p>
     * Getter for mapKeywordNameByNumber.
     * </p>
     * 
     * @return the mapKeywordNameByNumber
     */
    public Map getMapKeywordNameByNumber() {
        return mapKeywordNameByNumber;
    }

    /**
     * <p>
     * Setting value for mapKeywordNameByNumber.
     * </p>
     * 
     * @param mapKeywordNameByNumber the mapKeywordNameByNumber to set
     */
    public void setMapKeywordNameByNumber(Map mapKeywordNameByNumber) {
        this.mapKeywordNameByNumber = mapKeywordNameByNumber;
    }
    
    private String cacheKey(ReportCriteria rootCriteria){
    	StringBuilder temp=new StringBuilder();
    	temp.append("c:").append(rootCriteria.getCategory())
	    	.append("k:").append(rootCriteria.getKeyword())
	    	.append("u:").append(rootCriteria.getSourceUrl())
	    	.append("s:").append(rootCriteria.getStartDate())
	    	.append("e:").append(rootCriteria.getEndDate());
    	return temp.toString();
    }

}