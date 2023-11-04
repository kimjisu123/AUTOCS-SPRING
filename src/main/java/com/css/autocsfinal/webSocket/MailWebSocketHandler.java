package com.css.autocsfinal.webSocket;

import com.css.autocsfinal.mail.entity.Mail;
import com.css.autocsfinal.mail.repository.MailRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;

public class MailWebSocketHandler extends TextWebSocketHandler {

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // WebSocket 클라이언트로부터의 메시지 처리
    }
}