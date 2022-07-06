package com.revature.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * What is a DTO? Data Transfer Object Design Pattern
 * 
 * DTOs can either contain all the data from a source, or partial data. They can
 * hold data from single or multiple sources as well. When implemented, DTOs
 * become the means of data transport between systems.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Credentials {

	@NotNull
	@NotBlank
	private String username;

	@NotNull
	@NotBlank
	private String password;

}
