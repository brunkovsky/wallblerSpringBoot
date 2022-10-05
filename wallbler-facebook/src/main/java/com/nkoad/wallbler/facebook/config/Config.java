package com.nkoad.wallbler.facebook.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setUsername("stas");
        connectionFactory.setPassword("141292");
        connectionFactory.setHost("192.168.1.14");
        connectionFactory.setPort(5672);
        return connectionFactory;
    }

    @Bean
    public Queue wallblerFacebookQueue() {
        return new Queue("wallbler-feed-register");
    }

    @Bean
    public Queue facebookExecuteQueue() {
        return new Queue("facebook-execute-queue");
    }

    @Bean
    public DirectExchange facebookExecuteExchange() {
        return new DirectExchange("facebook-execute-exchange");
    }

    @Bean
    public Binding facebookExecuteBinding(Queue facebookExecuteQueue, DirectExchange facebookExecuteExchange) {
        return BindingBuilder.bind(facebookExecuteQueue).to(facebookExecuteExchange).with("facebook-execute");
    }

    @Bean
    public RabbitTemplate registerTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setRoutingKey("wallbler-feed-register");
        return rabbitTemplate;
    }

}
