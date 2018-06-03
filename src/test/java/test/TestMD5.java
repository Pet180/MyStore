package test;

import java.io.FileInputStream;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

public class TestMD5 {
	@Test
	public void test() throws Exception, Exception {
		String str1 = DigestUtils.md5Hex("123456");
		String str2 = DigestUtils.md5Hex("123456");
		System.out.println(str1);
		System.out.println(str2);
		
		String fileStr1 = DigestUtils.md5Hex(new FileInputStream("pom.xml"));
		String fileStr2 = DigestUtils.md5Hex(new FileInputStream("pom2.xml"));
//		System.out.println(fileStr1);
//		System.out.println(fileStr2);
		String pwd = "123456";
		String salt = "你喜歡編成嗎？";
		String strpwd = DigestUtils.md5Hex(pwd+salt);
		System.out.println(strpwd);
	}
}
