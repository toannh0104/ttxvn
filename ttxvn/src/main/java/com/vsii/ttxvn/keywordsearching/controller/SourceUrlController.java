/*
 * FILENAME
 *     SourceUrlController.java
 *
 * FILE LOCATION
 *     $Source$
 *
 * VERSION
 *     $Id$
 *         @version       $Revision$
 *         Check-Out Tag: $Name$
 *         Locked By:     $Lockers$
 *
 * FORMATTING NOTES
 *     * Lines should be limited to 78 characters.
 *     * Files should contain no tabs.
 *     * Indent code using four-character increments.
 *
 * COPYRIGHT
 *     Copyright (C) 2005 vietsoftware international Inc. All rights reserved.
 *     This software is the confidential and proprietary information of
 *     VSII ("Confidential Information"). You shall not
 *     disclose such Confidential Information and shall use it only in
 *     accordance with the terms of the license agreement you entered into
 *     with VSII.
 */

package com.vsii.ttxvn.keywordsearching.controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.vsii.ttxvn.keywordsearching.domain.CategoryItem;
import com.vsii.ttxvn.keywordsearching.domain.SourceUrlItem;
import com.vsii.ttxvn.keywordsearching.domain.TtxvnPageWrapper;
import com.vsii.ttxvn.keywordsearching.domain.TtxvnUser;
import com.vsii.ttxvn.keywordsearching.entity.Category;
import com.vsii.ttxvn.keywordsearching.entity.FetchFrequency;
import com.vsii.ttxvn.keywordsearching.entity.SourceUrl;
import com.vsii.ttxvn.keywordsearching.enums.CrawlingStatus;
import com.vsii.ttxvn.keywordsearching.enums.FetchDeep;
import com.vsii.ttxvn.keywordsearching.enums.SourceUrlType;
import com.vsii.ttxvn.keywordsearching.service.CategoryService;
import com.vsii.ttxvn.keywordsearching.service.FetchFrequencyService;
import com.vsii.ttxvn.keywordsearching.service.SourceUrlService;
import com.vsii.ttxvn.keywordsearching.utils.Constants;
import com.vsii.ttxvn.keywordsearching.utils.LanguageUtils;
import com.vsii.ttxvn.keywordsearching.utils.ServiceResolver;
import com.vsii.ttxvn.keywordsearching.utils.TtxvnUserContext;

/**
 * <p>
 * The class for purpose of handling all request about SourceUrl.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
@Controller
@RequestMapping("/manage/sourceUrl")
public class SourceUrlController extends BaseController {
	
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "")
	public ModelAndView viewSourceUrl() {
		return new ModelAndView("system-config/systemConfig");
	}

	@RequestMapping(value = "/getCategories")
	@ResponseBody
	public List<CategoryItem> getCategories(HttpServletRequest request,
			HttpServletResponse response) {
		List<Category> categories = getCategoryService()
				.getListCategoryByLanguage(
						LanguageUtils.getCurrentLanguageCode());
		List<CategoryItem> items = new ArrayList<CategoryItem>();
		int allUrlNo = 0;
		for (Category category : categories) {
			int urlNo = getCategoryService().getSourceUrlByCategoryLanguageCode(category, LanguageUtils.getCurrentLanguageCode())
					.size();
			allUrlNo += urlNo;
			CategoryItem item = new CategoryItem();
			item.setCategoryId(category.getId());
			item.setCategoryName(category.getName());
			item.setUrlNo(urlNo);
			items.add(item);
		}

		CategoryItem all = new CategoryItem();
		all.setCategoryName(this.messageSource.getMessage(
				"homepage.category.all", null, LocaleContextHolder.getLocale()));
		all.setUrlNo(allUrlNo);
		items.add(0, all);
		return items;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> createSourceUrl(HttpServletRequest request,
			HttpServletResponse response) {
		Long categoryId = ServletRequestUtils.getLongParameter(request,
				"category", -1);
		Long fetchFrequencyId = ServletRequestUtils.getLongParameter(request,
				"frequency", -1);
		String type = ServletRequestUtils.getStringParameter(request, "type",
				Constants.EMPTY_STRING);
		int deep = 0;
		try {
			deep = ServletRequestUtils.getIntParameter(request, "deep");
		} catch (ServletRequestBindingException e) {
			e.printStackTrace();
		}
		String reliability = ServletRequestUtils.getStringParameter(request,
				"reliability", Constants.EMPTY_STRING);
		String url = ServletRequestUtils.getStringParameter(request, "url",
				Constants.EMPTY_STRING);
				
		Long userId = ServletRequestUtils.getLongParameter(request, "user", -1);
		TtxvnUser currentUser = TtxvnUserContext.getCurrentUser();
		String username = Constants.EMPTY_STRING;
		if (currentUser != null) {
			userId = currentUser.getId();
			username = currentUser.getUsername();
		}

		Map<String, String> responeData = new HashMap<String, String>();
		String msg = Constants.EMPTY_STRING;
		String key = "message";
		msg = validateData(type, reliability, url);

		if (msg.isEmpty()) {
			if (getSourceUrlService().findByUrl(url) != null)
				msg = this.messageSource
						.getMessage(
								"ttxvn.keywordsearching.systemConfig.sourceurl.add.duplicate",
								null, LocaleContextHolder.getLocale());
			else {
				Category category = getCategoryService().findById(
						Category.class, categoryId);
				FetchFrequency frequency = getFetchFrequencyService().findById(
						FetchFrequency.class, fetchFrequencyId);
				SourceUrlType sourceType = SourceUrlType.NEWSPAPER;
				for (SourceUrlType urlType : SourceUrlType.values()) {
					if (urlType.getMessageCode().equals(type)) {
						sourceType = urlType;
						break;
					}
				}

				FetchDeep unidentified = FetchDeep.UNIDENTIFIED;
				FetchDeep deepToSearch = FetchDeep.ONE;

				for (FetchDeep deepSearch : FetchDeep.values()) {
					if (deepSearch == unidentified) {
						continue;
					} else if (deepSearch == null) {
						deepSearch = deepToSearch;
						break;
					} else if (deepSearch.ordinal() == deep) {
						deepToSearch = deepSearch;
						break;
					}
				}
				
				boolean www = url.contains("www.");
				boolean http = url.contains("http://");
				boolean httpwww = url.contains("http://www.");
				String subUrl = null;
				
				if (www) {
					subUrl = url.substring(4);
				} else if (http) {
					subUrl = url.substring(7);
				} else if (httpwww) {
					subUrl = url.substring(11);
				} else {
					subUrl = url;
				}

				String domain = getDomain(subUrl);

				SourceUrl sourceUrl = new SourceUrl(url,
						LanguageUtils.getCurrentLanguageCode(), userId,
						getReliability(reliability), sourceType, category,
						frequency, deepToSearch);
				sourceUrl.setDomain(domain);
				sourceUrl.setCrawlingStatus(CrawlingStatus.NOT_CRAWLING);
				getSourceUrlService().create(sourceUrl);

				LOG_WRITER.info(username + " CREATED Source URL "
						+ sourceUrl.getUrl());
			}
		}
		responeData.put(key, msg);
		return responeData;
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, String> updateSourceUrlPage(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, String> responseData = new HashMap<String, String>();
		long urlId = ServletRequestUtils.getLongParameter(request, "urlId", -1);
		SourceUrl sourceUrl = getSourceUrlService().findById(SourceUrl.class,
				urlId);
		if (sourceUrl != null) {
			responseData.put("urlId", sourceUrl.getId().toString());
			responseData.put("url", sourceUrl.getUrl());
			responseData.put("reliability", sourceUrl.getReliability()
					.toString());
			responseData.put("frequencyId", sourceUrl.getFetchFrequency()
					.getId().toString());
			String urlType = this.messageSource.getMessage(sourceUrl
					.getSourceType().getMessageCode(), null,
					LocaleContextHolder.getLocale());
			responseData.put("type", urlType);
			responseData.put("sourceType", sourceUrl.getSourceType()
					.getMessageCode());
			
			responseData.put("deep", sourceUrl.getFetchDeep().getValue().toString());
		}
		return responseData;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateSourceUrl(HttpServletRequest request,
			HttpServletResponse response) {
		long sourceUrlId = ServletRequestUtils.getLongParameter(request,
				"urlId", -1);
		SourceUrl sourceUrl = getSourceUrlService().findById(SourceUrl.class,
				sourceUrlId);
		String msg = Constants.EMPTY_STRING;
		if (sourceUrl != null) {
			final SourceUrl oldSourceUrl = this.getSourceUrlService().findById(
					SourceUrl.class, sourceUrlId);
			Long fetchFrequencyId = ServletRequestUtils.getLongParameter(
					request, "frequency", -1);
			String type = ServletRequestUtils.getStringParameter(request,
					"type", Constants.EMPTY_STRING);
			int deep = 0;
			try {
				deep = ServletRequestUtils.getIntParameter(request, "deep");
			} catch (ServletRequestBindingException e) {
				e.printStackTrace();
			}
			String reliability = ServletRequestUtils.getStringParameter(
					request, "reliability", Constants.EMPTY_STRING);
			String url = ServletRequestUtils.getStringParameter(request, "url",
					Constants.EMPTY_STRING);
			Long userId = ServletRequestUtils.getLongParameter(request, "user",
					-1);
			TtxvnUser currentUser = TtxvnUserContext.getCurrentUser();
			String username = Constants.EMPTY_STRING;
			if (currentUser != null) {
				userId = currentUser.getId();
				username = currentUser.getUsername();
			}

			msg = validateData(type, reliability, url);
			if (msg.isEmpty()) {
				if (!url.equals(oldSourceUrl.getUrl())
						&& getSourceUrlService().findByUrl(url) != null)
					msg = this.messageSource
							.getMessage(
									"ttxvn.keywordsearching.systemConfig.sourceurl.add.duplicate",
									null, LocaleContextHolder.getLocale());
				else {

					boolean www = url.contains("www.");
					boolean http = url.contains("http://");
					boolean httpwww = url.contains("http://www.");
					String subUrl = null;
					
					if (www) {
						subUrl = url.substring(4);
					} else if (http) {
						subUrl = url.substring(7);
					} else if (httpwww) {
						subUrl = url.substring(11);
					} else {
						subUrl = url;
					}

					String domain = getDomain(subUrl);

					SourceUrlType sourceType = SourceUrlType.NEWSPAPER;
					for (SourceUrlType urlType : SourceUrlType.values()) {
						if (urlType.getMessageCode().equals(type)) {
							sourceType = urlType;
							break;
						}
					}

					FetchDeep unidentified = FetchDeep.UNIDENTIFIED;
					FetchDeep deepToSearch = FetchDeep.ONE;

					for (FetchDeep deepSearch : FetchDeep.values()) {
						if (deepSearch == unidentified) {
							continue;
						} else if (deepSearch == null) {
							deepSearch = deepToSearch;
							break;
						} else if (deepSearch.ordinal() == deep) {
							deepToSearch = deepSearch;
							break;
						}
					}

					sourceUrl.setDomain(domain);
					sourceUrl.setUrl(url);
					sourceUrl.setReliability(getReliability(reliability));
					sourceUrl.setLastModifiedUserId(userId);
					sourceUrl.setSourceType(sourceType);
					sourceUrl.setFetchDeep(deepToSearch);
					sourceUrl.setLastModified(new Date());
					this.getSourceUrlService().update(sourceUrl,
							fetchFrequencyId);
					// write updated action to log file
					if (!oldSourceUrl.getUrl().equalsIgnoreCase(
							sourceUrl.getUrl())) {
						LOG_WRITER.info(username
								+ " UPDATED Source URL changed URL from ["
								+ oldSourceUrl.getUrl() + "] to ["
								+ sourceUrl.getUrl() + "]");
					}

					if (oldSourceUrl.getReliability() != sourceUrl
							.getReliability()) {
						LOG_WRITER
								.info(username
										+ " UPDATED Source URL changed Reliability from ["
										+ oldSourceUrl.getReliability()
										+ "] to [" + sourceUrl.getReliability()
										+ "]");
					}

					if (oldSourceUrl.getSourceType() != sourceUrl
							.getSourceType()) {
						LOG_WRITER
								.info(username
										+ " UPDATED Source URL changed Source Type from ["
										+ oldSourceUrl.getSourceType()
										+ "] to [" + sourceUrl.getSourceType()
										+ "]");
					}

					if (oldSourceUrl.getFetchFrequency().getId() != sourceUrl
							.getFetchFrequency().getId()) {
						LOG_WRITER
								.info(username
										+ " UPDATED Source URL changed FetchFrequency: from ["
										+ oldSourceUrl.getFetchFrequency()
												.getFrequency()
										+ " minutes] to ["
										+ sourceUrl.getFetchFrequency()
												.getFrequency() + " minutes]");
					}
				}
			}

		}
		Map<String, String> responseData = new HashMap<String, String>();
		responseData.put("message", msg);
		return responseData;
	}

	@RequestMapping(value = "/deleteUrl", method = RequestMethod.POST)
	public void deleteSourceUrl(HttpServletRequest request) {
		String username = Constants.EMPTY_STRING;
		TtxvnUser currentUser = TtxvnUserContext.getCurrentUser();
		if (currentUser != null) {
			username = currentUser.getUsername();
		}
		String[] ids = request.getParameterValues("items[]");
		for (int i = 0; i < ids.length; i++) {
			long id = Long.parseLong(ids[i]);
			final SourceUrl sourceUrl = this.getSourceUrlService().findById(SourceUrl.class, id);
			if (sourceUrl != null) {
				this.getSourceUrlService().delete(SourceUrl.class, id);
				// write create SourceURL to log file
				LOG_WRITER.info(username + " DELETED Source URL {URL: "
						+ sourceUrl.getUrl() + "}");
			}
		}

	}

	@RequestMapping(value = "/findURLByPage")
	@ResponseBody
	public TtxvnPageWrapper<SourceUrlItem> findURLByPage(
			HttpServletRequest request, HttpServletResponse response) {
		int page = ServletRequestUtils.getIntParameter(request, Constants.PAGE,
				1);
		long categoryId = ServletRequestUtils.getLongParameter(request,
				"category", -1);
		String urlSearching = ServletRequestUtils.getStringParameter(request,
				"url", Constants.EMPTY_STRING);

		String orderBy = "createDate";
		Sort sort = new Sort(Sort.Direction.ASC, orderBy);
		PageRequest pageRequest = new PageRequest(page - 1, 10, sort);

		TtxvnPageWrapper<SourceUrlItem> pageWrapper = new TtxvnPageWrapper<SourceUrlItem>(
				getSourceUrlService().getAllSourceUrlByCategoryLanguage(
						categoryId, LanguageUtils.getCurrentLanguageCode(),
						urlSearching, pageRequest), "/findURLByPage", 5);
		List<SourceUrlItem> items = pageWrapper.getContent();
		for (SourceUrlItem item : items) {
			item.setSourceTypeCode(this.messageSource.getMessage(
					item.getSourceTypeCode(), null,
					LocaleContextHolder.getLocale()));
		}


		return pageWrapper;
	}

	/**
	 * <p>
	 * Getter for sourceUrlService.
	 * </p>
	 * 
	 * @return the sourceUrlService
	 */
	public SourceUrlService getSourceUrlService() {
		return (SourceUrlService) ServiceResolver
				.findService(SourceUrlService.class);
	}

	/**
	 * <p>
	 * Getter for sourceUrlService.
	 * </p>
	 * 
	 * @return the sourceUrlService
	 */
	public CategoryService getCategoryService() {
		return (CategoryService) ServiceResolver
				.findService(CategoryService.class);
	}

	/**
	 * <p>
	 * Getter for fetchFrequencyService.
	 * </p>
	 * 
	 * @return the fetchFrequencyService
	 */
	public FetchFrequencyService getFetchFrequencyService() {
		return (FetchFrequencyService) ServiceResolver
				.findService(FetchFrequencyService.class);
	}

	private int getReliability(String reliability) {
		int value = -1;
		try {
			value = Integer.parseInt(reliability);
		} catch (NumberFormatException e) {
			return -1;
		}
		return value;
	}

	private String getUrl(String url) {
		String regex = "^(https?://)?([\\da-z\\.-]+).([a-z\\.]{2,6})([\\/\\w \\.-]*)*\\/?$";
		if (!url.matches(regex))
			return Constants.EMPTY_STRING;

		if (url.length() > 1000)
			return Constants.EMPTY_STRING;

		return url;
	}

	private String validateData(String type, String reliability, String url) {
		if (type.isEmpty() || reliability.isEmpty() || url.isEmpty())
			return this.messageSource
					.getMessage(
							"ttxvn.keywordsearching.systemConfig.sourceurl.add.required",
							null, LocaleContextHolder.getLocale());

		int reliabilityValue = getReliability(reliability);
		if (reliabilityValue < 0 || reliabilityValue > 100)
			return this.messageSource
					.getMessage(
							"ttxvn.keywordsearching.systemConfig.sourceurl.add.invalid1",
							null, LocaleContextHolder.getLocale());

		String URL_REGEX = "^((https?|ftp)://|(www|ftp)\\.)?[a-z0-9-]+(\\.[a-z0-9-]+)+([/?].*)?$";
		Pattern p = Pattern.compile(URL_REGEX);
		
		Matcher m = p.matcher(url);
		if(!(m.find())) {
			return this.messageSource
					.getMessage(
							"ttxvn.keywordsearching.systemConfig.sourceurl.add.invalid2",
							null, LocaleContextHolder.getLocale());
		}

		return Constants.EMPTY_STRING;
	}

	private String getDomain(String url) {
		StringBuilder domain = new StringBuilder(url);
		if (url.indexOf("http") < 0) {
			domain = new StringBuilder("http://");
			domain.append(url);
		}

		try {
			URL tempUrl = new URL(domain.toString());
			return tempUrl.getHost();
		} catch (MalformedURLException e) {
			return Constants.EMPTY_STRING;
		}
	}
	
	@RequestMapping(value = "/loadSourceUrl", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, String> findSourceUrlByCategory(HttpServletRequest request, HttpServletResponse response)
	{
		List<SourceUrl> sourceUrls = getSourceUrlService().getListSourceUrlByLanguage(
				LanguageUtils.getCurrentLanguageCode());
		Map<String, String> values = new HashMap<String, String>();
		
		for (SourceUrl source : sourceUrls)
		{
			values.put(source.getId().toString(), source.getDomain());
		}
		return values;
	}
}
