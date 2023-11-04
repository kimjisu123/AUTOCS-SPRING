package com.css.autocsfinal.config;

import com.css.autocsfinal.webSocket.MailWebSocketHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;

/** WebSocket 엔드포인트 및 메시지 처리기를 설정합니다. */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer{

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // WebSocketHandlerRegistry를 사용하여 WebSocket 핸들러를 등록하는 메소드

        // WebSocketHandler 클래스를 WebSocket 핸들러로 등록하고 "/mail-ws" 엔드포인트 경로 매핑
        // 클라이언트는 WebSocket 연결을 "/mail-ws" 엔드포인트로 생성하여 이 핸들러를 사용할 수 있다.
        registry.addHandler(new MailWebSocketHandler(), "/mail-ws") // 1번째 인자에는 웹소켓 핸들러, 2번째 인자에는 엔드포인트 경로
                .setAllowedOrigins("*") // cors same-origin만 허용
                .withSockJS();          // SockJS 사용하도록 설정
    }
}