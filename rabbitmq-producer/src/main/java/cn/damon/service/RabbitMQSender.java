package cn.damon.service;

import cn.damon.model.Employee;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Value("${rabbitmq.exchange}")
	private String exchange;
	
	@Value("${rabbitmq.bus1.routingkey1}")
	private String routingkey;
	
	public <T> void send(T data) {
		amqpTemplate.convertAndSend(exchange, routingkey, data);
		System.out.println("Send msg = " + data);
	    
	}
}