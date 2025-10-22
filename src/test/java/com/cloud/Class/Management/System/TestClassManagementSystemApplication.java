package com.cloud.Class.Management.System;

import org.springframework.boot.SpringApplication;

public class TestClassManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.from(ClassManagementSystemApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
