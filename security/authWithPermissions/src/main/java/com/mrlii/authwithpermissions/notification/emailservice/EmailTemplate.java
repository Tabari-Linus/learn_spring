package com.mrlii.authwithpermissions.notification.emailservice;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmailTemplate {

    SEND_VERIFY_EMAIL("verify_email"),
    SEND_WELCOME_EMAIL("welcome"),
    SEND_FORGOT_PASSWORD_EMAIL("forgot_password"),
    SEND_RESET_PASSWORD_EMAIL("reset_password")    ;

    private static final String  SYSTEM_MESSAGE = "System Team";
    private final String name;
    private final String subject;


    EmailTemplate(String name) {
        this.name = name;
        this.subject = SYSTEM_MESSAGE;
    }
}
