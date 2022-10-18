package com.highschool.image.vote;

import com.highschool.image.vote.config.GoogleAPIKey;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImageVoteApplication {

	public static void main(String[] args)  {
		GoogleAPIKey.setSecretCredentials(args[0], args[1]);
		SpringApplication.run(ImageVoteApplication.class, args);
	}
}
