package com.ppx.ppxshiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true)
@ComponentScan("com.ppx.ppxshiro.config")
public class PpxShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(PpxShiroApplication.class, args);
    }

}
