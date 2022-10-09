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
        connectionFactory.setHost("192.168.1.14");
        connectionFactory.setPort(5672);
        return connectionFactory;
    }

    @Bean
    public Queue wallblerFacebookQueue() {
        return new Queue("wallbler-type-register");
    }

    @Bean
    public RabbitTemplate executeRabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

//    @Bean
//        public RabbitTemplate registerTemplate(ConnectionFactory connectionFactory) {
//        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setExchange("wallbler-register");
//        rabbitTemplate.setRoutingKey("register-feed");
//        return rabbitTemplate;
//    }

//    @Bean
//    public RestHighLevelClient client() {
//        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
//                .connectedTo("localhost:9200")
//                .build();
//        return RestClients.create(clientConfiguration).rest();
//    }
//
//    @Bean
//    public ElasticsearchOperations elasticsearchTemplate() {
//        return new ElasticsearchRestTemplate(client());
//    }

}
