package com.deiksoftdev.automatagame.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotBlank(message = "Name is required")
	@Column(nullable = false, unique = true)
	private String name;
	
	@NotBlank(message = "Email is required")
	@Column(nullable = false, unique = true)
	private String email;

	@NotBlank(message = "Password is required")
	@Column(nullable = false)
	private String password;
}