package com.example.webrtc.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
//@EnableWebSocket
//@RequiredArgsConstructor
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

//    private final WebSocketHandler webSocketHandler;


    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws/chat")
                .withSockJS();
    }

    public void configureMessageBroker(MessageBrokerRegistry registry){
        registry.enableSimpleBroker("/sub");

        registry.setApplicationDestinationPrefixes("/pub");
    }

//    @Bean
//    public ServletServerContainerFactoryBean createWebSocketContainer(){
//        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
//        container.setMaxBinaryMessageBufferSize(8192);
//        container.setMaxBinaryMessageBufferSize(8192);
//        return container;
//    }
}
