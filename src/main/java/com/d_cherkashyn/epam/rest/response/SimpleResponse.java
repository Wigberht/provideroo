package com.d_cherkashyn.epam.rest.response;


public class SimpleResponse {
    private boolean success;
    
    public SimpleResponse(boolean success) {
        this.success = success;
    }
    
    public boolean isSuccess() {
        return success;
    }
    
    public void setSuccess(boolean success) {
        this.success = success;
    }
}
