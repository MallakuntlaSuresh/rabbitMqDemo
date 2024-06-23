package com.rabbit.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqConsumer {
	private static final Logger logger = LoggerFactory.getLogger(RabbitMqConsumer.class);

	@RabbitListener(queues = { "${queue.name}" })
	public void consumer(String message) {
		logger.info(String.format("Message Recieved -> %s", message));
	}
}
