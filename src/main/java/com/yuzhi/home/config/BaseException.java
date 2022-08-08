package com.yuzhi.home.config;

public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String code;

    public BaseException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public BaseException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
        this.code = "500";
    }

    public BaseException(String message) {
        super(message);
        this.code = "500";
    }

    public BaseException() {
        super("业务访问异常");
        this.code = "500";
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Throwable fillInStackTrace() {
        return this;
    }

    public String toString() {
        return this.getClass().getSimpleName() + "==>" + this.getMessage();
    }
}
