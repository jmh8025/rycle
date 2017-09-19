package kr.spring.tiles.util;
 
public interface MailService {
    boolean send(String subject, String text, String from, String to);
    
    boolean check(String authNum,String auth);

	
}


