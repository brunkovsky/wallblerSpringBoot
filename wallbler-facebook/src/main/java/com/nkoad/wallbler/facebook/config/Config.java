package com.nkoad.wallbler.facebook.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
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
//        connectionFactory.setHost("83.10.232.89");
        connectionFactory.setPort(5672);
        return connectionFactory;
    }

    @Bean
    public DirectExchange facebookExecutorExchange() {
        return new DirectExchange("facebook-executor-exchange");
    }

    @Bean
    public Queue facebookExecutorQueue() {
        return new Queue("facebook-executor-queue");
    }

    @Bean
    public Binding facebookExecutorBinding(DirectExchange facebookExecutorExchange, Queue facebookExecutorQueue) {
        return BindingBuilder.bind(facebookExecutorQueue)
                .to(facebookExecutorExchange)
                .with("facebook-executor");
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
        return BindingBuilder.bind(facebookAccessTokenQueue)
                .to(facebookAccessTokenExchange)
                .with("facebook-access-token");
    }

}
