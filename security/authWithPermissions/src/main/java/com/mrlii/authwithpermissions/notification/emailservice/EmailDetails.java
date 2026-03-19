package com.mrlii.authwithpermissions.notification.emailservice;

public record EmailDetails(
        String recipient,
        String email,
        EmailTemplate template
) {
}
