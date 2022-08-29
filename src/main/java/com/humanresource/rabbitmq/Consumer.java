package com.humanresource.rabbitmq;

import com.humanresource.model.Employee;
import com.humanresource.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * @author umeshkhatiwada13@infodev
 * @project human-resource
 * @created 29/08/2022 - 06:18
 */
public class Consumer {
    private static final String ROUTING_KEY = "mail.queue.key";
    private final EmailService emailService;

    public Consumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = ROUTING_KEY)
    public void receiveMessage(Employee employee) throws Exception {
        emailService.sendMail(employee);
    }
}
