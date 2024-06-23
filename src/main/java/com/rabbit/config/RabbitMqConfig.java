package com.rabbit.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

	@Value("${queue.name}")
	private String queue;
	@Value("${json.queue.name}")
	private String jsonQueue;
	@Value("${exchange.name}")
	private String exchange;

	@Value("${routing.name}")
	private String routingKey;

	@Value("${json.routing.name}")
	private String jsonRoutingKey;

	@Bean
	public org.springframework.amqp.core.Queue queue() {
		return new org.springframework.amqp.core.Queue(queue);
	}

	@Bean
	public org.springframework.amqp.core.Queue jsonQueue() {
		return new org.springframework.amqp.core.Queue(jsonQueue);
	}

	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(exchange);
	}

	@Bean
	public Binding binding() {
		return BindingBuilder.bind(queue()).to(exchange()).with(routingKey);
	}

	@Bean
	public Binding jsonBinding() {
		return BindingBuilder.bind(queue()).to(exchange()).with(jsonRoutingKey);
	}

	@Bean
	MessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}

	public  RabbitTemplate amqpTemplate(org.springframework.amqp.rabbit.connection.ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(converter());
		return rabbitTemplate;
	}
}
