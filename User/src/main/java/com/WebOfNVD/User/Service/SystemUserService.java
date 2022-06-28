package com.WebOfNVD.User.Service;

import java.util.List;

import com.WebOfNVD.Common.Response.SuccessResponse;
import com.WebOfNVD.User.Dto.SystemUser.CreateSystemUserRequest;
import com.WebOfNVD.User.Dto.SystemUser.ListSystemUserCode;
import com.WebOfNVD.User.Dto.SystemUser.SystemUserDto;
import com.WebOfNVD.User.Dto.SystemUser.UpdateSystemUserRequest;

public interface SystemUserService {
	SuccessResponse create(CreateSystemUserRequest request);

	List<SystemUserDto> findAllSystemUser();

	SuccessResponse softDeleteSystemUser(ListSystemUserCode systemUserCodes);

	SuccessResponse updateAllField(String systemUserCode, UpdateSystemUserRequest request);
}
