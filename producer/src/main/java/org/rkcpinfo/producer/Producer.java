package org.rkcpinfo.producer;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.rkcpinfo.producer.incoming.EngineeringPublisher;
import org.rkcpinfo.producer.model.Engineering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;

@SpringBootApplication
public class Producer {

	@Autowired
	private EngineeringPublisher EngineeringPublisher;

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new SpringApplicationBuilder(Producer.class).web(false).run(args);
		context.getBean(Producer.class).run(context);
		context.close();
	}

	private void run(ConfigurableApplicationContext context) {

		System.out.println("Inside ProducerApplication run method...");

		MessageChannel producerChannel = context.getBean("producerChannel", MessageChannel.class);

		List<Engineering> engineerings = EngineeringPublisher.getEngineerings();

		for (Engineering engineering : engineerings) {
			Map<String, Object> headers = Collections.singletonMap(KafkaHeaders.TOPIC, engineering.getGenre().toString());
			producerChannel.send(new GenericMessage<>(engineering.toString(), headers));
		}

		System.out.println("Finished ProducerApplication run method...");
	};
}
