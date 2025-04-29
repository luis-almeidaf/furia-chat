package com.project.furia_chat.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    
    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
