package com.ppro.pproprojectfinal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PProProjectFinalApplication {

    public static void main(String[] args) {
        SpringApplication.run(PProProjectFinalApplication.class, args);
    }

}
