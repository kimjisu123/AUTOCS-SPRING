package com.css.autocsfinal.mail.service;

import com.css.autocsfinal.mail.dto.MailDTO;
import com.css.autocsfinal.mail.dto.MailListDTO;
import com.css.autocsfinal.mail.entity.Mail;
//import com.css.autocsfinal.mail.entity.MailList;
//import com.css.autocsfinal.mail.repository.MailListRepository;
import com.css.autocsfinal.mail.repository.MailListRepository;
import com.css.autocsfinal.mail.repository.MailRepository;
import com.css.autocsfinal.member.entity.Employee;
import com.css.autocsfinal.member.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MailService {

    private final MailRepository mailRepository;
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final MailListRepository mailListRepository;

    public List<MailDTO> findMail() {

        List<Mail> mailList = mailRepository.findAll();

        List<MailDTO> mailDTOList = mailList.stream().map(Mail -> modelMapper.map(Mail, MailDTO.class) ).collect(Collectors.toList());

        return mailDTOList;
    }

    public Object mailBookmark() {

        List<Mail> mailList = mailRepository.findByStatus("Y");
        log.info("============================================================= mailDTOList" + mailList);

        List<MailDTO> mailDTOList = mailList.stream().map(mail -> modelMapper.map(mail, MailDTO.class)).collect(Collectors.toList());

        log.info("============================================================= mailDTOList" + mailDTOList);

        return mailDTOList;

    }
    @Transactional
    public Object saveMail(MailDTO mailDTO) {

        mailDTO.setGoDate(new Date());
        mailDTO.setStatus("N");
        mailDTO.setContext(mailDTO.getContext().replace("<p>", ""));
        mailDTO.setContext(mailDTO.getContext().replace("</p>", ""));

        Mail mail = modelMapper.map(mailDTO, Mail.class);
        mailRepository.save(mail);

        return null;

    }

    public Object deleteMail() {

        mailRepository.deleteAll();

        return null;
    }

    @Transactional
    public Object setMail(MailDTO mailDTO) {

        int mailNo = mailDTO.getMailNo();

        Mail mail = mailRepository.findById(mailNo).get();

        if(mail.getStatus().equals("N")){
            mail.setStatus("Y");
        } else {
            mail.setStatus("N");
        }

        MailDTO resultMail = modelMapper.map(mail, MailDTO.class);

        return resultMail;

    }


    public Object deleteSelectMail(MailDTO mailDTO) {

        int mailNo = mailDTO.getMailNo();

        mailRepository.deleteById(mailNo);

        return null;
    }

    public Object mailSent(int employeeNo) {

        List<Mail> mail = mailListRepository.findMailListByEmployeeNo(employeeNo);

        log.info("===========================> {}",mail);

        return null;
    }
}