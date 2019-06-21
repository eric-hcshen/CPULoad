package com.tsmc.ta.cpuload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CPULoadApplication {
	public static void main(String[] args) {
		SpringApplication.run(CPULoadApplication.class, args);
	}
}