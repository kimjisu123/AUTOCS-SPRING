package com.css.autocsfinal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

/** WebSocket 엔드포인트 및 메시지 처리기를 설정합니다. */
@Configuration
@EnableWebSocket
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/webSocket").setAllowedOriginPatterns("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry){
        registry.enableSimpleBroker("/queue", "/topic"); // queue(prefix) 메시지 1:1 topic 브로드캐스팅 : 1:N 혹은 1:전체
        registry.setApplicationDestinationPrefixes("/app");
    }

}