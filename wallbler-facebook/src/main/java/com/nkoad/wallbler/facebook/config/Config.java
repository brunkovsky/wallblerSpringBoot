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
//        connectionFactory.setHost("192.168.1.14");
        connectionFactory.setHost("83.29.81.59");
        connectionFactory.setPort(5672);
        return connectionFactory;
    }

    @Bean
    public DirectExchange facebookExecuteExchange() {
        return new DirectExchange("facebook-execute-exchange");
    }

    @Bean
    public Queue facebookExecuteQueue() {
        return new Queue("facebook-execute-queue");
    }

    @Bean
    public Binding facebookExecuteBinding(DirectExchange facebookExecuteExchange, Queue facebookExecuteQueue) {
        return BindingBuilder.bind(facebookExecuteQueue).to(facebookExecuteExchange).with("facebook-execute");
    }

    @Bean
    public DirectExchange facebookAccessTokenExchange() {
        return new DirectExchange("facebook-access-token-exchange");
    }

    @Bean
    public Queue facebookAccessTokenQueue() {
        return new Queue("facebook-access-token-queue");
    }

    @Bean
    public Binding facebookAccessTokenBinding(DirectExchange facebookAccessTokenExchange, Queue facebookAccessTokenQueue) {
        return BindingBuilder.bind(facebookAccessTokenQueue).to(facebookAccessTokenExchange).with("facebook-access-token");
    }

}
