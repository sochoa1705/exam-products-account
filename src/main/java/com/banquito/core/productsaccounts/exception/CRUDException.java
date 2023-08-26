package com.banquito.core.productsaccounts.exception;

public class CRUDException extends RuntimeException{

    private final Integer errorCode;
    
    public CRUDException(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public CRUDException(Integer errorCode, String arg0) {
        super(arg0);
        this.errorCode = errorCode;
    }

    public CRUDException(Integer errorCode, String arg0, Throwable arg1) {
        super(arg0, arg1);
        this.errorCode = errorCode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }
    
    

}
