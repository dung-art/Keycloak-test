package com.WebOfNVD.User.Ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.WebOfNVD.Common.Response.FailResponse;
import com.WebOfNVD.Common.Response.ResponseObject;
import com.WebOfNVD.Common.Response.SuccessResponse;
import com.WebOfNVD.User.Dto.SystemUser.CreateSystemUserRequest;
import com.WebOfNVD.User.Dto.SystemUser.ListSystemUserCode;
import com.WebOfNVD.User.Dto.SystemUser.SystemUserDto;
import com.WebOfNVD.User.Dto.SystemUser.UpdateSystemUserRequest;
import com.WebOfNVD.User.Service.SystemUserService;

@RestController
@RequestMapping("/api/v1/users")
@Validated
@Transactional
public class UserCtrl {

	@Autowired
	private SystemUserService systemUserService;

// SystemUser
	@PostMapping("/system-user")
	@ResponseStatus(HttpStatus.OK)
	public SuccessResponse create(@RequestBody CreateSystemUserRequest request) {
		return systemUserService.create(request);
	}

	@GetMapping("/system-user/all-system-user")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public SuccessResponse findAllSystemUser() {
		List<SystemUserDto> dtos = systemUserService.findAllSystemUser();
		if (dtos == null || dtos.isEmpty()) {
			return new FailResponse(
					"Không tìm thấy tài khoản người dùng nào hoặc các tài khoản người dùng đã được xóa !");
		}
		return new ResponseObject<>(dtos);
	}

	@PostMapping("/system-user/delete-system-user")
	@ResponseStatus(HttpStatus.OK)
	public SuccessResponse softDeleteSystemUser(@RequestBody ListSystemUserCode systemUserCodes) {
		return systemUserService.softDeleteSystemUser(systemUserCodes);
	}

	@PostMapping("/system-user/{id}/update-system-user")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public SuccessResponse updateAllFieldSystemUser(@PathVariable("id") String systemUserCode,
			@RequestBody UpdateSystemUserRequest request) throws Exception {
		return systemUserService.updateAllField(systemUserCode, request);
	}

//	@PostMapping("/system-user/add-role/system-user")
//	@ResponseStatus(HttpStatus.OK)
//	public SuccessResponse addRoleForSystemUser(@RequestBody CreateSystemUserRequest request) {
//		return systemUserService.create(request);
//	}
//	
//	@PostMapping("/system-user/add-group/system-user")
//	@ResponseStatus(HttpStatus.OK)
//	public SuccessResponse addSystemUserInGroup(@RequestBody CreateSystemUserRequest request) {
//		return systemUserService.create(request);
//	}

}
