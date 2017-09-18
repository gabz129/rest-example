package com.gabz129.restexample;

public enum MessageCodes {
    CREATE_CONTACT_OK("Contact created successful"),
    CREATE_CONTACT_ERROR("Creation contact failed"),
    RETRIEVE_CONTACT_ERROR("Retrieve contact failed"),
    RETRIEVE_CONTACT_NOT_FOUND("Contact to retrieve not found"),
    UPDATE_CONTACT_ERROR("Updating contact failed"),
    UPDATE_CONTACT_NOT_FOUND("Contact to update not found"),
    DELETE_CONTACT_OK("Deleting contact successful"),
    DELETE_CONTACT_ERROR("Deleting contact failed");

    private final String message;

    MessageCodes(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
