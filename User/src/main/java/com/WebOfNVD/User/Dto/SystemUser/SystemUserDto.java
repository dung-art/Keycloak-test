package com.WebOfNVD.User.Dto.SystemUser;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SystemUserDto {
	private Boolean isDelete;
	private String userAdd;
	private LocalDateTime dateAdd;
	private LocalDateTime modifiedDate;
	private String modifiedUser;
	private String modifiedAction;
	private String systemUserCode;
	private String fullName;
	private String userName;
	private String identifyNumber;
	private LocalDateTime birthDate;
	private String password;
	private String pathImage;
	private String roleOriginSystemUser;
	private String groupUser;
	private String description;
}
