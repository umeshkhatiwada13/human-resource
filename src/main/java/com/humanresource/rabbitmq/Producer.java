package com.humanresource.rabbitmq;

/**
 * @author umeshkhatiwada13@infodev
 * @project human-resource
 * @created 29/08/2022 - 06:03
 */

import com.humanresource.model.Employee;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
    private static final String EXCHANGE_NAME = "common.queue";
    private static final String ROUTING_KEY = "mail.queue.key";
    private final RabbitTemplate rabbitTemplate;

    public Producer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void produceMessage(Employee employee) {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, employee);
        System.out.println("Mail-" + employee.getName() + " has been sent to the queue.");
    }
}
