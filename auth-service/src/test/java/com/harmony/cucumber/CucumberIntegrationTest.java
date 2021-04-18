package com.harmony.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {
                "src/test/resources/features"
        },
        glue = "com.harmony.cucumber.features",
        plugin = {"pretty"}
)
public class CucumberIntegrationTest {
}

