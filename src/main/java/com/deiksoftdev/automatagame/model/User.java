package com.deiksoftdev.automatagame.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User {

	@Transient
	public static final String SEQUENCE_NAME = "users_sequence";

	@Id
	private Long id;
	
	@NotBlank(message = "Name is required")
	private String name;
	
	@NotBlank(message = "Email is required")
	private String email;

	@NotBlank(message = "Password is required")
	private String password;

	private boolean admin;

	private boolean disabled;

	private Map<String,Integer> scores;

	public Integer getScore(String scenario){
		return scores.getOrDefault(scenario,0);
	}

	public void setScore(String scenario, Integer score) {
		scores.put(scenario,score);
	}
}