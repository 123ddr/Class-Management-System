package com.cloud.Class.Management.System;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(properties = "testcontainers.mysql.enabled=false")
class ClassManagementSystemApplicationTests {

	@Test
	void contextLoads() {
	}

}
