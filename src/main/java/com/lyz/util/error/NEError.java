package com.lyz.util.error;


public enum NEError implements IError {
    SYSTEM_INTERNAL_ERROR("001", "服务器内部错误"),
    INVALID_PARAMETER("002", "参数错误"),
    PAGER_IS_NULL("003", "分页参数不能为空"),
    PAGER_PARAMETER_IS_NOT_CORRECT("004", "分页参数不正确"),
    METHOD_NOT_SUPPORTED("005", "不支持的方法"),
    SERVICE_NOT_FOUND("006", "未找到相关服务"),
    INVALID_STATUS("007", "错误的状态"),
    WRITE_EXCEL_ERROR("008", "write excel error"),
    READ_EXCEL_ERROR("009", "read excel error"),
    CONFIG_IS_NOT_CORRECT("010", "系统配置错误"),
    NOT_SUPPORT("011", "不支持的请求"),
    CALL_REMOTE_ERROR("012", "微服务调用错误"),
    CONTENT_TYPE_NOT_SUPPORT("013", "不支持的Content-Type"),
    INVALID_TOKEN("014", "非法Token"),
    DATA_NOT_FOUND("015", "未找到相关资源"),
    CALL_REMOTE_TIMEOUT_ERROR("016", "微服务调用超时"),
    TOKEN_EXPIRED("017", "Token过期或失效"),
    UNKNOWN_ERROR("018", "未知错误"),
    BUSINESS_ERROR("10000000", "业务处理错误");

    String errorCode;
    String errorMessage;

    private NEError(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getNamespace() {
        return null;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }
}