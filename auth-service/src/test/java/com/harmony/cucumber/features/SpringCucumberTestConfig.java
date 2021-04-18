package com.harmony.cucumber.features;

import com.harmony.authservice.AuthServiceApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = AuthServiceApplication.class)
public class SpringCucumberTestConfig {
}
