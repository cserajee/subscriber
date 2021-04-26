package com.subscriber.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.inbound.PubSubInboundChannelAdapter;

@Component
public class GCPConfig {

	   private static final Log LOGGER = LogFactory.getLog(GCPConfig.class);
	
	   @Bean
	   public PubSubInboundChannelAdapter messageChannelAdapter(
	         @Qualifier("myInputChannel") MessageChannel inputChannel,
	         PubSubTemplate pubSubTemplate) {
	      PubSubInboundChannelAdapter adapter =
	            new PubSubInboundChannelAdapter(pubSubTemplate, "GCPTopic-sub");
	      adapter.setOutputChannel(inputChannel); 
	      return adapter;
	   }

	   @Bean
	   public MessageChannel myInputChannel() {
	      return new DirectChannel();
	   }
 
	   @ServiceActivator(inputChannel = "myInputChannel")
	   public void messageReceiver(String payload) {
	      LOGGER.info("Message arrived! Payload: " + payload);

	   }
	   
}
