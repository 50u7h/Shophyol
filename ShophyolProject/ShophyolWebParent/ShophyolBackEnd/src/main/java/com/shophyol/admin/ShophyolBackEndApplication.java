package com.shophyol.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.shophyol.common.entity","com.shophyol.admin.user"})
public class ShophyolBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShophyolBackEndApplication.class, args);
	}

}
