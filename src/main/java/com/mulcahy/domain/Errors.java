package com.mulcahy.domain;

/**
 * Created by Chris on 11/24/2016.
 */
public enum Errors {

    CATEGORY("Category","Category error failed to load category please try again."),
    ADDCATEGORY("AddCategory","Category error failed to add category please try again."),
    ADDGIF("AddGif","Failed to add gif please try again."),
    LOGINERROR("LoginError","Failed to validate user information please try again.");

    private final String name;
    private final String message;

    Errors(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

}
