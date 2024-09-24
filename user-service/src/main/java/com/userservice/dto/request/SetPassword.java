package com.userservice.dto.request;

import lombok.Data;

@Data
public class SetPassword {
    
    private String username;
    
    private String password;

}
