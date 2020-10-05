package com.qa.TDL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.qa.TDL.TDLApplication;

@SpringBootApplication
@EnableWebMvc // - enables Swagger UI at <host>:<port>/swagger-ui/index.html
public class TDLApplication {
	
	public static void main(String[] args) {
        SpringApplication.run(TDLApplication.class, args);
    }


}
