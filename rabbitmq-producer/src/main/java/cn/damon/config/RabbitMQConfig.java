package cn.damon.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	@Value("${rabbitmq.exchange}")
	private String exchange;

	@Value("${rabbitmq.bus1.routingkey1}")
	private String bus1Routingkey1;

	@Value("${rabbitmq.bus1.queue1}")
	private String bus1Queue1;



	@Bean
    Queue bus1Queue1() {
		return new Queue(bus1Queue1, true);
	}

	@Bean
    DirectExchange exchange() {
		return new DirectExchange(exchange);
	}

	@Bean
    Binding bus1Routingkey1Binding(Queue queue, DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(bus1Routingkey1);
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}
}
