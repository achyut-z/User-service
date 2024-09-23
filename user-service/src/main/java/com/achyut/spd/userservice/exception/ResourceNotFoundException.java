package com.achyut.spd.userservice.exception;


public class ResourceNotFoundException extends RuntimeException {
    
    public ResourceNotFoundException() {
        super();
    }
    
    public ResourceNotFoundException(String t) {
        super(t + " NOT FOUND");
    }

}
