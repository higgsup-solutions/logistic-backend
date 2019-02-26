package com.higgsup.base.security.exception;
import org.springframework.security.core.AuthenticationException;

/**
 * JwtTokenNotValid
 * 
 * @author vladimir.stankovic
 *
 * Aug 17, 2016
 */
public class InvalidJwtToken extends AuthenticationException {
    private static final long serialVersionUID = -294671188037098603L;


    public InvalidJwtToken(String msg) {
        super(msg);
    }
}
