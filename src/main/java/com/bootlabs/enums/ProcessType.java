package com.bootlabs.enums;

import lombok.Getter;

@Getter
public enum ProcessType {
    NEWSLETTER("NewsletterTemplate"),
    WELCOME("WelcomeTemplate");

    private final String templateName;
    ProcessType(String templateName) {
        this.templateName = templateName;
    }
}