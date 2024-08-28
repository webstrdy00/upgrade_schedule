package com.webstrdy00.upgrade_schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@EnableJpaAuditing
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
@ServletComponentScan
@SpringBootApplication
public class UpgradeScheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(UpgradeScheduleApplication.class, args);
    }

}
