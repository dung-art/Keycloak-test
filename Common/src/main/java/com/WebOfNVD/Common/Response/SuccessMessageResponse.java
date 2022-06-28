package com.WebOfNVD.Common.Response;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessMessageResponse extends SuccessResponse implements Serializable {

	public SuccessMessageResponse(String message) {
		super();
		this.message = message;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	
	
}
