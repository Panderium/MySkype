package com.uqac.my_skype;

import com.uqac.my_skype.network.ServerP2P;
import com.uqac.my_skype.utils.ConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class MySkypeApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MySkypeApplication.class, args);
    }

    @Bean
    public ConnectionFactory socketFactory() {
        return new ConnectionFactory();
    }

    @Bean
    public ServerP2P serverP2P() {
        return new ServerP2P();
    }


}
