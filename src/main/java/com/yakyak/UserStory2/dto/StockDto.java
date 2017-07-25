package com.yakyak.UserStory2.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockDto {
	Double milk;
	Integer skins;
}
