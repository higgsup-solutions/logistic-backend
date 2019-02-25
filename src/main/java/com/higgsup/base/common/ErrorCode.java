package com.higgsup.base.common;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enumeration of REST Error types.
 * 
 * @author vladimir.stankovic
 *
 *         Aug 3, 2016
 */
public enum ErrorCode {
    GLOBAL(2),
    VALIDATION(1),
    AUTHENTICATION(10),
    JWT_TOKEN_EXPIRED(11),
    DUPPLICATE_EMAIL(12),
    DUPPLICATE_USERNAME(13),
    USER_NOT_FOUND(15),
    OLD_PASSWORD_OR_NEW_PASSWORD_INVALID(16),
    DUPPLICATE_ADDRESS(14);

    private int errorCode;

    ErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @JsonValue
    public int getErrorCode() {
        return errorCode;
    }
}
