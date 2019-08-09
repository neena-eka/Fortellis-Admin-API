package com.cdk.dc.hello;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(strict = false, features = "src/test/resources/features", plugin = { "pretty",
    "html:target/site/cucumber-pretty",
    "json:target/dc-cloud-service-seed.json"}, tags = { "not @ignore" })
public class CucumberIntegrationTest {

}
