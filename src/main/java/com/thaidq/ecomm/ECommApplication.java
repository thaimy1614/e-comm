package com.thaidq.ecomm;

import com.thaidq.ecomm.services.FileSystemStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ECommApplication {

    public static void main(String[] args) {
        SpringApplication.run(ECommApplication.class, args);
    }

    @Bean
    CommandLineRunner init(FileSystemStorageService fileSystemStorageService){
        return (args) -> fileSystemStorageService.init();
    }


}
