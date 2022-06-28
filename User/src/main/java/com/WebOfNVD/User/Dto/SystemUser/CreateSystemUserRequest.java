package com.WebOfNVD.User.Dto.SystemUser;

import java.time.LocalDateTime;

import com.WebOfNVD.Common.Convert.Convert;
import com.googlecode.jmapper.annotations.JMapConversion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateSystemUserRequest {
	private String systemUserCode;
	private String fullName;
	private String userName;
	private String identifyNumber;
	private String birthDate;
	private String password;
	private String pathImage;
//	private String roleOriginSystemUser;
	private String groupUser;
	private String description;

	@JMapConversion(from = { "birthDate" }, to = { "birthDate" })
	public LocalDateTime conversion(String birthDate) throws Exception {
		try {
			return Convert.convertStringDateToLocalDateTime(birthDate);
		} catch (Exception e) {
			return null;
		}
	}
}
