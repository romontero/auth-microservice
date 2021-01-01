package com.domain;

public enum Authority {
    ROLE_ADMIN,
    ROLE_USER;

    public static final String ROLE_ADMIN_STRING = "ROLE_ADMIN";
    public static final String ROLE_USER_STRING = "ROLE_USER";

    public String getShortForm(){
        return name().replace("ROLE_","");
    }
}
