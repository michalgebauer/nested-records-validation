package com.example.demo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@SpringBootApplication
@EnableConfigurationProperties({ClientProperties.class/*, ClientPropertiesWorking.class*/})
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

// with this, application starts successfully
@Validated
@ConfigurationProperties(prefix = "client")
record ClientProperties(ServiceOne serviceOne) {

	@Validated
	public record ServiceOne(@NotBlank String url, @Email String email) {
	}

}

// when uncommented, the application fails to start with the following error:
// Binding to target org.springframework.boot.context.properties.bind.BindException: Failed to bind properties under 'working' to com.example.demo.ClientPropertiesWorking failed:
//
//    Property: working.url
//    Value: "null"
//    Reason: must not be blank

//@Validated
//@ConfigurationProperties(prefix = "working")
//record ClientPropertiesWorking(@NotBlank String url, @Email String email) {
//}