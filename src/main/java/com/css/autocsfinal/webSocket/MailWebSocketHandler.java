package com.css.autocsfinal.webSocket;

import com.css.autocsfinal.mail.entity.Mail;
import com.css.autocsfinal.mail.repository.MailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;

@Component
public class MailWebSocketHandler extends TextWebSocketHandler {

    private final SimpMessagingTemplate messagingTemplate;
    private final MailRepository mailRepository;

    public MailWebSocketHandler(SimpMessagingTemplate messagingTemplate, MailRepository mailRepository) {
        this.messagingTemplate = messagingTemplate;
        this.mailRepository = mailRepository;
    }


    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // WebSocket 클라이언트로부터의 메시지 처리
    }

    public void sendUnreadMailNotification() {

        List<Mail> unreadMails = mailRepository.findByRead("N");

        if (!unreadMails.isEmpty()) {
            String notificationMessage = "읽지 않은 메일의 수는 " + unreadMails.size() + "개 입니다.";
            messagingTemplate.convertAndSend("/topic/mail-notifications", notificationMessage);
        }

    }
}