package com.sub.sb6;

import static org.junit.jupiter.api.Assertions.*;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DatabaseConnectTest {
	
	@Autowired
	private DataSource dataSource;

	@Test
	void test() throws Exception{
		assertNotNull(dataSource.getConnection());
	}

}
