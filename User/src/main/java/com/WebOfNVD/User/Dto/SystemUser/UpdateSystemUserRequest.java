package com.WebOfNVD.User.Dto.SystemUser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateSystemUserRequest {

	private String identifyNumber;

	private String pathImage;

	private String roleOriginSystemUser;

	private String groupUser;

	private String description;
}
