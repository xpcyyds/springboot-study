package com.cdcompany.controller;

public class Result {

    private Integer code;
    private Object data;
    private String msg;
    private Long total;
    private String token;
    private String refreshToken;

    public Result(String msg,Object data) {
        this.data = data;
        this.msg = msg;
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(Object data, String msg, String token) {
        this.data = data;
        this.msg = msg;
        this.token = token;
    }

    public Result(Object data, String msg, String token, String refreshToken) {
        this.data = data;
        this.msg = msg;
        this.token = token;
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Result(Integer code, Object data, String msg, Long total) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.total = total;
    }

    public Result(Object data) {
        this.data = data;
    }

    public Result() {
    }

    public Result(Integer code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public Result(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
