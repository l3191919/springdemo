package com.lyz.util.error;


public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = -6293662498600553602L;
    private IError error;
    private String extMessage;

    public BusinessException() {
        this.error = NEError.BUSINESS_ERROR;
        this.extMessage = null;
    }

    public BusinessException(String message) {
        super(message);
        this.error = NEError.BUSINESS_ERROR;
        this.extMessage = null;
        this.extMessage = message;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.error = NEError.BUSINESS_ERROR;
        this.extMessage = null;
        this.extMessage = message;
    }

    public BusinessException(Throwable cause) {
        super(cause);
        this.error = NEError.BUSINESS_ERROR;
        this.extMessage = null;
        if (cause instanceof BusinessException) {
            BusinessException fe = (BusinessException)cause;
            this.error = fe.getError();
            this.extMessage = fe.getMessage();
        }

    }

    public BusinessException(IError error) {
        super(error.getErrorMessage());
        this.error = NEError.BUSINESS_ERROR;
        this.extMessage = null;
        this.error = error;
    }

    public BusinessException(IError error, String message) {
        super(error.getErrorMessage());
        this.error = NEError.BUSINESS_ERROR;
        this.extMessage = null;
        this.error = error;
        this.extMessage = message;
    }

    public BusinessException(Throwable cause, IError error, String message) {
        super(message, cause);
        this.error = NEError.BUSINESS_ERROR;
        this.extMessage = null;
        this.extMessage = message;
        this.error = error;
    }

    public BusinessException(IError error, Throwable cause) {
        super(cause);
        this.error = NEError.BUSINESS_ERROR;
        this.extMessage = null;
        this.error = error;
    }

    public IError getError() {
        return this.error;
    }

    public String getExtMessage() {
        return this.extMessage;
    }

    public void setExtMessage(String extMessage) {
        this.extMessage = extMessage;
    }

    public String toString() {
        return super.toString() + ",ErrorCode : " + this.error.getErrorCode() + ", ErrorMessage : " + this.error.getErrorMessage() + ", ExtMessage : " + this.extMessage;
    }
}