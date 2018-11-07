package com.uqac.my_skype;

import com.uqac.my_skype.network.Connection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import java.io.IOException;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class MySkypeApplication {

	public static void main (String[] args) throws Exception{

		SpringApplication.run(MySkypeApplication.class, args);
		try {
			new Thread(new Connection("127.0.0.1",2222)).start();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
