package com.example.app.wallet;

import com.example.app.wallet.config.annotation.DeveloperInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@DeveloperInfo(
        expertise = DeveloperInfo.Expertise.JUNIOR,
        createdBy = "Abkode",
        url = "https://github.com/abkaytan",
        email = "a.buyukkaytan@gmail.com"

)
public class WalletDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WalletDemoApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
