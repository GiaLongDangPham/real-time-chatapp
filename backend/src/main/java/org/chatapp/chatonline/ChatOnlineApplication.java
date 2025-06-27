package org.chatapp.chatonline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ChatOnlineApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatOnlineApplication.class, args);
    }

}
