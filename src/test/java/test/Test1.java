package test;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import com.huishouwu.util.email.EmailSender;

public class Test1 {
	
	@Test
	public void a(){
		EmailSender es=new EmailSender();
		es.sendPass(RandomStringUtils.randomNumeric(8), RandomStringUtils.randomAlphanumeric(8), "fei.zheng@agrant.cn");
	}
}
