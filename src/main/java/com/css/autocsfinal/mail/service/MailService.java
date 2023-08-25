package com.css.autocsfinal.mail.service;

import com.css.autocsfinal.mail.dto.MailDTO;
import com.css.autocsfinal.mail.entity.Mail;
import com.css.autocsfinal.mail.repository.MailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MailService {

    private final MailRepository mailRepository;
    private final ModelMapper modelMapper;

    public List<MailDTO> findMail() {

        List<Mail> mailList = mailRepository.findAll();

        List<MailDTO> mailDTOList = mailList.stream().map(Mail -> modelMapper.map(Mail, MailDTO.class) ).collect(Collectors.toList());

        return mailDTOList;
    }

    public Object mailBookmark() {
//        List<Mail> mailList = mailRepository.findByStatus("N");

        List<Mail> mailList = mailRepository.findByStatus("N    ");
        log.info("============================================================= mailDTOList" + mailList);

        List<MailDTO> mailDTOList = mailList.stream().map(mail -> modelMapper.map(mail, MailDTO.class)).collect(Collectors.toList());

        log.info("============================================================= mailDTOList" + mailDTOList);

        return mailDTOList;

    }

    public Object saveMail(MailDTO mailDTO1) {


        Mail mail = modelMapper.map(mailDTO1, Mail.class);
        mailRepository.save(mail);

        return null;

    }

    public Object deleteMail() {

        mailRepository.deleteAll();

        return null;
    }

    @Transactional
    public Object setMail(MailDTO mailDTO) {

        log.info("==================================================== DTO 값 확인 : " + mailDTO);

        int mailNo = mailDTO.getMailNo();

        Mail mail = mailRepository.findById(mailNo).get();

        if(mail.getStatus().equals("N")){
            mail.setStatus("Y");
        } else {
            mail.setStatus("N");
        }

        log.info("======================================= mailEntity 수정 and 값 확인 " + mail);

        MailDTO resultMail = modelMapper.map(mail, MailDTO.class);

        return resultMail;

    }

}
