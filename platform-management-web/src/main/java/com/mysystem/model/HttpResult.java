package com.mysystem.model;

import lombok.Data;

@Data
public class HttpResult<T> {

    private int code;
    private String message;
    private T data;

    public HttpResult() {
    }

    public HttpResult(int code, String message, T data) {
        this.code = code;
        this.setMessage(message);
        this.data = data;
    }

    public HttpResult(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public HttpResult(int code, String message) {
        this.code = code;
        this.setMessage(message);
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> HttpResult success(T data) {
        HttpResult result = new HttpResult<>();
        result.setCode(200);
        result.setMessage("success");
        result.setData(data);
        return result;
    }

    public static HttpResult success() {
        HttpResult result = new HttpResult<>();
        result.setMessage("success");
        result.setCode(200);
        return result;
    }

    public static <T> HttpResult fail(T data) {
        HttpResult result = new HttpResult<>();
        result.setCode(518);
        result.setMessage("fail");
        result.setData(data);
        return result;
    }

    public static <T> HttpResult fail() {
        HttpResult result = new HttpResult<>();
        result.setCode(518);
        result.setMessage("fail");
        return result;
    }

    public String toString() {
        return "RestResult{code=" + this.code + ", message='" + this.message + '\'' + ", data=" + this.data + '}';
    }

}
