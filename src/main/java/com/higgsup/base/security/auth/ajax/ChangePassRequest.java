package com.higgsup.base.security.auth.ajax;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ChangePassRequest {
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;

    @JsonCreator
    public ChangePassRequest(@JsonProperty("oldPassword") String oldPassword,
                             @JsonProperty("newPassword") String newPassword, @JsonProperty("confirmPassword") String confirmPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
}
