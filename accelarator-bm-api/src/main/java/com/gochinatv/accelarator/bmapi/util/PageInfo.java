package com.gochinatv.accelarator.bmapi.util;
/**
 * 
 * <dt>PageInfo</dt>
 * <dd>Description:分页信息</dd>
 */
public class PageInfo {
	/*** 总记录数 */
	private long totalRecords = 0;
	/** 显示记录开始数 */
	private int startIndex = 0;
	/** 页面显示记录条数 */
	private int results = 10;
	/*** 排序项 */
	private String sortItem;
	/*** 排序方式 */
	private String sortType;
	
	private int limit = 0;
	
	private int curPage;
	
	public int getLimit(){
		return results;
	}
	
	public void setLimit(int limit) {
		this.limit = limit;
	}

	public PageInfo(){
		
	}
    /**
     * @param start
     * @param results
     * @param sortItem
     * @param sortType
     */
	public PageInfo(int start,int results,String sortItem,String sortType){
		this.startIndex=start;
		this.results=results;
		this.sortItem=sortItem;
		this.sortType=sortType;
	}
	/**
	 * @param startIndex
	 * @param results
	 */
	public PageInfo(int startIndex,int results){
		this.startIndex=startIndex;
		this.results=results;
		
		this.curPage=this.startIndex/this.results+1;
	}
	public int getResults() {
		return results;
	}

	public void setResults(int results) {
		this.results = results;
	}

	public String getSortItem() {
		return sortItem;
	}

	public void setSortItem(String sortItem) {
		this.sortItem = sortItem;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public long getTotalRecords() {
		return totalRecords;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}
	
	public int getTotalPage(){
		if(totalRecords == 0){
			return 0;
		}else{
			return (int)((totalRecords+limit-1)/getLimit());
		}
		
	}
}
