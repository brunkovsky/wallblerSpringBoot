package com.nkoad.wallbler.main.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.nkoad.wallbler.main.repository")
@ComponentScan
public class WallblerConfig {

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
    public RabbitTemplate executeRabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

}
