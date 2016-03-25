
package com.vsii.ttxvn.keywordsearching.domain;

import java.util.Date;

/**
 * <p>
 * The class for purpose of carry user info these presentations in View layer.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
public class ReportItem {
	private String keyword;
	private String category;
	private String url;
	private String startDate;
	private String endDate; // hold Vietnamese content
	private int numOfNews;
	private int numOfAppearance;
	/**
	 * <p>
	 * Getter for keyWord.
	 * </p>
	 * 
	 * @return the keyWord
	 */
	
	
	
	public String getKeyWord() {
		return keyword;
	}
	/**
	 * <p>
	 * Add one-sentence summarising the constructor inputs here; this
	 * sentence should only contain one full-stop.
	 * </p>
	 * <p>
	 * Add detailed HTML description of constructor here, including the
	 * following, where relevant:
	 * <ul>
	 *     <li>Description of what the constuctor does and how it is done.</li>
	 *     <li>Details on which error conditions may occur.</li>
	 * </ul>
	 * </p>
	 *
	 * @param keyWord
	 * @param category
	 * @param url
	 * @param startDate
	 * @param endDate
	 * @param numOfNews
	 * @param numOfAppearance
	 **/
	public ReportItem(String keyword, String category, String url,
			String startDate, String endDate, int numOfNews, int numOfAppearance) {
		super();
		this.keyword = keyword;
		this.category = category;
		this.url = url;
		this.startDate = startDate;
		this.endDate = endDate;
		this.numOfNews = numOfNews;
		this.numOfAppearance = numOfAppearance;
	}
	/**
	 * <p>
	 * Setting value for keyWord.
	 * </p>
	 * 
	 * @param keyWord the keyWord to set
	 */
	public void setKeyWord(String keyword) {
		this.keyword = keyword;
	}
	/**
	 * <p>
	 * Getter for category.
	 * </p>
	 * 
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * <p>
	 * Setting value for category.
	 * </p>
	 * 
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * <p>
	 * Getter for url.
	 * </p>
	 * 
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * <p>
	 * Setting value for url.
	 * </p>
	 * 
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * <p>
	 * Getter for startDate.
	 * </p>
	 * 
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * <p>
	 * Setting value for startDate.
	 * </p>
	 * 
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	 * <p>
	 * Getter for endDate.
	 * </p>
	 * 
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 * <p>
	 * Setting value for endDate.
	 * </p>
	 * 
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	/**
	 * <p>
	 * Getter for numOfNews.
	 * </p>
	 * 
	 * @return the numOfNews
	 */
	public int getNumOfNews() {
		return numOfNews;
	}
	/**
	 * <p>
	 * Setting value for numOfNews.
	 * </p>
	 * 
	 * @param numOfNews the numOfNews to set
	 */
	public void setNumOfNews(int numOfNews) {
		this.numOfNews = numOfNews;
	}
	/**
	 * <p>
	 * Getter for numOfAppearance.
	 * </p>
	 * 
	 * @return the numOfAppearance
	 */
	public int getNumOfAppearance() {
		return numOfAppearance;
	}
	/**
	 * <p>
	 * Setting value for numOfAppearance.
	 * </p>
	 * 
	 * @param numOfAppearance the numOfAppearance to set
	 */
	public void setNumOfAppearance(int numOfAppearance) {
		this.numOfAppearance = numOfAppearance;
	}

	

}
