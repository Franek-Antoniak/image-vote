package com.highschool.image.vote.config;

import java.io.*;
import java.nio.file.Paths;

public class GoogleAPIKey {
	public static void setSecretCredentials(String secretCredentials, String credentials) {
		File file = Paths.get("src/main/resources/application.properties")
		                 .toFile();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			StringBuilder builder = new StringBuilder();
			while (line != null) {
				if (line.equals("spring.security.oauth2.client.registration.google.client-secret=")) {
					builder.append("spring.security.oauth2.client.registration.google.client-secret=")
					       .append(secretCredentials)
					       .append("\n");
				} else if (line.equals("spring.security.oauth2.client.registration.google.client-id=")) {
					builder.append("spring.security.oauth2.client.registration.google.client-id=")
					       .append(credentials)
					       .append("\n");
				} else {
					builder.append(line)
					       .append("\n");
				}
				line = reader.readLine();
			}
			reader.close();
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(builder.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
