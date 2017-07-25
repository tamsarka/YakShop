package com.yakyak.UserStory2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class HerdDto {
	String name;
	float age;
	@JsonProperty(value= "age-last-shaved")
	float ageLastShaved;
	
}
