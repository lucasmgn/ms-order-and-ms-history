package br.com.compass.sprint6.mshistory.mshistory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class MsHistoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsHistoryApplication.class, args);
	}

}
