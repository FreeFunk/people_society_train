package com.edgedo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
@ComponentScan("com.edgedo")
public class SysWxCommonApplication {

	public static void main(String[] args) {
		SpringApplication.run(SysWxCommonApplication.class, args);
	}


}
