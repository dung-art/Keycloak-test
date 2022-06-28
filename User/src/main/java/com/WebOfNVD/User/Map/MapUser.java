package com.WebOfNVD.User.Map;

import com.WebOfNVD.User.Dto.SystemUser.UpdateSystemUserRequest;
import com.WebOfNVD.User.Entity.SystemUser;

public class MapUser {

	public static SystemUser getSystemUserFromUpdateUSer(UpdateSystemUserRequest request, SystemUser systemUser) {
		if (request == null) {
			return null;
		}
		if (request.getIdentifyNumber() != null) {
			systemUser.setIdentifyNumber(request.getIdentifyNumber());
		}
		if (request.getPathImage() != null) {
			systemUser.setPathImage(request.getPathImage());
		}
		if (request.getGroupUser() != null) {
			systemUser.setGroupUser(request.getGroupUser());
		}
		if (request.getRoleOriginSystemUser() != null) {
			systemUser.setRoleOriginSystemUser(request.getRoleOriginSystemUser());
		}
		if (request.getDescription() != null) {
			systemUser.setDescription(request.getDescription());
		}
		return systemUser;
	}
}
