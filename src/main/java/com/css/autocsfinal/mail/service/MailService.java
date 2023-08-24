package com.css.autocsfinal.mail.service;

import com.css.autocsfinal.mail.dto.MailDTO;
import com.css.autocsfinal.mail.entity.Mail;
import com.css.autocsfinal.mail.repository.MailRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MailService {

    private final MailRepository mailRepository;
    private final ModelMapper modelMapper;

    public List<MailDTO> findMail() {

        List<Mail> mailList = mailRepository.findAll();

        List<MailDTO> mailDTOList = mailList.stream().map(Mail -> modelMapper.map(Mail, MailDTO.class) ).collect(Collectors.toList());

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
}
