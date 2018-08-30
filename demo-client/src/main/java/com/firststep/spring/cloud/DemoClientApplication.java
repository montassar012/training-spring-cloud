package com.firststep.spring.cloud;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class DemoClientApplication {

	@Autowired
	private EurekaClient client;

	@Autowired
	RestTemplateBuilder restTemplateBuilder;

	public static void main(String[] args) {
		SpringApplication.run(DemoClientApplication.class, args);
	}

	@RequestMapping("/")
	public String callService(){

		RestTemplate restTemplate = restTemplateBuilder.build();
		InstanceInfo instanceInfo = client.getNextServerFromEureka("service",false);
		String baseUrl= instanceInfo.getHomePageUrl();
		ResponseEntity<String> responseEntity=restTemplate.exchange(baseUrl, HttpMethod.GET,null,String.class);
		return responseEntity.getBody();
	}
}
