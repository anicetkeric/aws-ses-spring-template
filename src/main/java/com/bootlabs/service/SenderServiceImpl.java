package com.bootlabs.service;

import com.bootlabs.model.EmailNotificationSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class SenderServiceImpl implements SenderService {

    private final MailService mailService;

    @Override
    public void sendEmail(EmailNotificationSender notificationSender) {
        mailService.sendEmailTemplate(notificationSender);
    }

}
