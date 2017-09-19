package kr.spring.tiles.util;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import kr.spring.tiles.member.controller.MemberController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
 
@Service
public class MailServiceImpl implements MailService {
 
    private final JavaMailSender javaMailSender;
    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
    @Autowired
    public MailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
 
    @Override
    public boolean send(String subject, String text, String from, String to){
        MimeMessage message = javaMailSender.createMimeMessage();
 
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true,"UTF-8");
            helper.setSubject(subject);
            helper.setText(text);
            helper.setFrom(from);
            helper.setTo(to);
            javaMailSender.send(message);
            logger.info("성공");
            return true;
        }catch (MessagingException e){
            e.printStackTrace();
            
        }
        logger.info("실패");
        return false;
     
    }

	@Override
	public boolean check(String authNum,String auth) {
		if(auth.equals(authNum)) {
		   	 return true;
			}else {
				return false;
			}
	}
}


