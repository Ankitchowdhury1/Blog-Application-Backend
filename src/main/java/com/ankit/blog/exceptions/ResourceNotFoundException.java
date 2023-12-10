package com.ankit.blog.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
	
	String resoueceName;
	String fieldName;
	long fieldValue;
	public ResourceNotFoundException(String resoueceName, String fieldName, long fieldValue) {
		super(String.format("%s is not found with %s : %s", resoueceName, fieldName, fieldValue ));
		this.resoueceName = resoueceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	

}
