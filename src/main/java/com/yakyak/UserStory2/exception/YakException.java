package com.yakyak.UserStory2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.GONE,
reason = " Internal problem occored during parsing")
public class YakException extends Exception{

}
