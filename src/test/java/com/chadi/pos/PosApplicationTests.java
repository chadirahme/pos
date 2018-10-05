package com.chadi.pos;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PosApplicationTests {

	@Test
	public void contextLoads() {
		BillFormat billFormat=new BillFormat();
		String result = billFormat.test();//testDialog();
		Assert.assertEquals(result,"test");
	}

}
