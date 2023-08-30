package com.css.autocsfinal.market.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;

@Service
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String storeEmail, String newUserId, String newPassword) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom("eun06151@naver.com", "AUTOCS");

            helper.setTo(storeEmail);
            helper.setSubject("신청하신 계정이 발급되었습니다.");

            String emailContent = "<html>"
                    + "<head>"
                    + "<style>"
                    + "body { font-family: Arial, sans-serif; margin: 0; padding: 0; }"
                    + ".container { max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #FFFFFF; background-color: white; text-align: center; }"
                    + "h1 { color: black; margin-bottom: 10px; font-weight: bold; margin-left: 20px; }"
                    + "p { margin-top: 10px; font-size: 18px; font-weight: bold; }"
                    + ".whole { background-color: #2A3C1E; }"
                    + ".button-container { margin-top: 30px; }"
                    + ".custom-button { background-color: #2A3C1E; color: #FFF; padding: 15px 25px; text-decoration: none; border-radius: 5px; font-size: 15px; font-weight: bold; border: none; cursor: pointer; }"
                    + ".custom-button:hover { background-color: #1C2716; }"
                    + ".image-container { display: flex; justify-content: center; align-items: center; }"
                    + ".image { width: 120px; height: 160px; }"
                    + ".separator { border-bottom: 1px solid #1C2C10; margin-bottom: 20px; margin-top: -10px; }"
                    + "</style>"
                    + "</head>"
                    + "<body>"
                    + "<div class=\"whole\">"
                    + "<div class=\"image-container\">"
                    + "<img src='cid:logo' class='image'/>"
                    + "</div>"
                    + "</div>"
                    + "<div class=\"container\">"
                    + "<h1>안녕하세요, 새로운 계정 정보입니다.</h1>"
                    + "<div class=\"separator\"></div>"
                    + "<p style=\"font-size: 18px;\">아이디: " + newUserId + "</p>"
                    + "<p style=\"font-size: 18px;\">임시 비밀번호: " + newPassword + "</p>"
                    + "<div class=\"button-container\">"
                    + "<button class=\"custom-button\"><a href=\"http://localhost:3000/login\" style=\"color: white; text-decoration: none;\">로그인하러가기</a></button>"
                    + "</div>"
                    + "</div>"
                    + "</body>"
                    + "</html>";

            helper.setText(emailContent, true);
            // 이미지를 추가합니다.
            helper.addInline("logo", new ClassPathResource("loginMain.png"));

            mailSender.send(message);

            System.out.println("Email sent successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error sending email: " + e.getMessage());
        }
    }

}
