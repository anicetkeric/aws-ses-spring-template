package com.bootlabs.service;

import com.bootlabs.model.EmailNotificationSender;

public interface SenderService {
   void sendEmail(EmailNotificationSender notificationSender);
}
