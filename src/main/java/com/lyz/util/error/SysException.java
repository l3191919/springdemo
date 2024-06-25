package com.lyz.util.error;


public class SysException extends RuntimeException {
    private static final long serialVersionUID = -6293662498600553602L;
    private IError error;
    private String extMessage;

    public SysException() {
        this.error = NEError.SYSTEM_INTERNAL_ERROR;
        this.extMessage = null;
    }

    public SysException(String message) {
        super(message);
        this.error = NEError.SYSTEM_INTERNAL_ERROR;
        this.extMessage = null;
        this.extMessage = message;
    }

    public SysException(String message, Throwable cause) {
        super(message, cause);
        this.error = NEError.SYSTEM_INTERNAL_ERROR;
        this.extMessage = null;
        this.extMessage = message;
    }

    public SysException(Throwable cause) {
        super(cause);
        this.error = NEError.SYSTEM_INTERNAL_ERROR;
        this.extMessage = null;
        if (cause instanceof SysException) {
            SysException fe = (SysException)cause;
            this.error = fe.getError();
            this.extMessage = fe.getMessage();
        }

    }

    public SysException(IError error) {
        super(error.getErrorCode() + ":" + error.getErrorMessage());
        this.error = NEError.SYSTEM_INTERNAL_ERROR;
        this.extMessage = null;
        this.error = error;
    }

    public SysException(IError error, String message) {
        super(error.getErrorCode() + ":" + error.getErrorMessage());
        this.error = NEError.SYSTEM_INTERNAL_ERROR;
        this.extMessage = null;
        this.error = error;
        this.extMessage = message;
    }

    public SysException(Throwable cause, IError error, String message) {
        super(message, cause);
        this.error = NEError.SYSTEM_INTERNAL_ERROR;
        this.extMessage = null;
        this.extMessage = message;
        this.error = error;
    }

    public SysException(IError error, Throwable cause) {
        super(cause);
        this.error = NEError.SYSTEM_INTERNAL_ERROR;
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