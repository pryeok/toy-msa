package com.example.userservice;

import com.example.userservice.error.FeignErrorDecoder;
import feign.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.time.Duration;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(DataSource dataSource) {
		return args -> {
			System.out.println("URL: " + dataSource.getConnection().getMetaData().getURL());
		};
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@LoadBalanced // 이 애노테이션을 통해서 url: http://order-service/order-service/%s/orders 이것처럼 url을 유레카에 등록된 msa 네임으로 가능
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Bean // Feign Client 에 대한 로그 객체 설정 빈 주입
	public Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}

//	@Bean // 이 부분은 FeignErrorDecoder클래스에서 @Component로 빈 주입
//	public FeignErrorDecoder getFeignErrorDecoder() {
//		return new FeignErrorDecoder();
//	}
}
