package test;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

public class Test1 {
	
	@Test
	public void a(){
		System.out.println(RandomStringUtils.randomNumeric(10));
	}
}
