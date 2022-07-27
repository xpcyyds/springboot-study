package com.cdcompany.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QueryPage {
    @JsonProperty(value = "query")
    private String query;
    @JsonProperty(value = "pageNum")
    private Integer pageNum;
    @JsonProperty(value = "pageSize")
    private Integer pageSize;

    public QueryPage(String query, Integer pageNum, Integer pageSize) {
        this.query = query;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public QueryPage() {
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
