package com.WebOfNVD.User.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.WebOfNVD.Common.Checks.Checks;
import com.WebOfNVD.Common.Convert.Convert;
import com.WebOfNVD.Common.Response.FailResponse;
import com.WebOfNVD.Common.Response.ResponseObject;
import com.WebOfNVD.Common.Response.SuccessMessageResponse;
import com.WebOfNVD.Common.Response.SuccessResponse;
import com.WebOfNVD.User.Dto.CreateUserResponse;
import com.WebOfNVD.User.Dto.SystemUser.CreateSystemUserRequest;
import com.WebOfNVD.User.Dto.SystemUser.ListSystemUserCode;
import com.WebOfNVD.User.Dto.SystemUser.SystemUserDto;
import com.WebOfNVD.User.Dto.SystemUser.UpdateSystemUserRequest;
import com.WebOfNVD.User.Entity.SystemUser;
import com.WebOfNVD.User.Map.MapUser;
import com.WebOfNVD.User.Repo.SystemUserRepo;
import com.googlecode.jmapper.JMapper;

@Service
@Transactional
public class SystemUserServiceImpl implements SystemUserService {
	@Autowired
	SystemUserRepo systemUserRepo;

	private JMapper<SystemUserDto, SystemUser> mapper = new JMapper<>(SystemUserDto.class, SystemUser.class);
//	private JMapper<SystemUser, CreateSystemUserRequest> cmapper = new JMapper<>(SystemUser.class,
//			CreateSystemUserRequest.class);

	@Override
	public SuccessResponse create(CreateSystemUserRequest request) {
		try {
			Optional<SystemUser> oSystemUser = systemUserRepo.findById(request.getSystemUserCode());
			if (oSystemUser.isPresent()) {
				return new FailResponse("Thông tin về người dùng này đã tồn tại !");
			}
			if (!request.getIdentifyNumber().equals(null)
					&& systemUserRepo.findByIdentifyNumber(request.getIdentifyNumber()).isPresent()) {
				return new FailResponse("Số định danh cá nhân không được trùng lặp !");
			}
			if (!Checks.checkStringIsFormatDate(request.getBirthDate())) {
				return new FailResponse("Ngày không đúng định dạng ! (Vui lòng nhập theo định dạng : dd/MM/yyyy)");
			} else {
				if (Checks.isSystemUserCode(request.getSystemUserCode())) {
					SystemUser systemUser = new SystemUser();
					PropertyUtils.copyProperties(systemUser, request);
					systemUser.setRoleOriginSystemUser(Convert.getRoleOriginSystemUser(request.getSystemUserCode()));
					systemUserRepo.save(systemUser);
					return new CreateUserResponse(systemUser.getSystemUserCode(),
							"Thêm mới tài khoản người dùng thành công !");
				}
				return new FailResponse("Vui lòng nhập đúng định dạng mã người dùng hệ thống: SYS + xx + yyy !");
			}
		} catch (Exception e) {
			return new FailResponse("Đã có lỗi xãy ra !");
		}
	}

	@Override
	public SuccessResponse softDeleteSystemUser(ListSystemUserCode systemUserCodes) {
		List<SystemUser> systemUsers = systemUserRepo.findAllById(systemUserCodes.getSystemUserCodes());
		if (systemUsers.isEmpty() || systemUsers == null) {
			return new FailResponse(
					"Không tìm thấy tài khoản người dùng nào được chỉ định hoặc không có tài khoản người dùng nào được xóa !");
		}
		for (SystemUser systemUser : systemUsers) {
			systemUser.setIsDelete(true);
		}
		return new SuccessMessageResponse("Xóa thành công !");
	}

	@Override
	public List<SystemUserDto> findAllSystemUser() {
		List<SystemUser> systemUsers = systemUserRepo.findAllNoDelete();
		if (systemUsers.isEmpty() || systemUsers == null) {
			return null;
		}
		List<SystemUserDto> systemUserDtos = new ArrayList<>();
		for (SystemUser systemUser : systemUsers) {
			systemUserDtos.add(mapper.getDestination(systemUser));
		}
		return systemUserDtos;
	}

	@Override
	public SuccessResponse updateAllField(String systemUserCode, UpdateSystemUserRequest request) {
		try {
			Optional<SystemUser> oSystemUser = systemUserRepo.findById(systemUserCode);
			if (!oSystemUser.isPresent()) {
				return new FailResponse("Tài khoản người dùng chưa tồn tại!");
			}
			if (oSystemUser.get().getIsDelete()) {
				return new FailResponse("Tài khoản người dùng đã bị xóa !");
			} else {
				if (oSystemUser.get().getIdentifyNumber().equals(request.getIdentifyNumber())
						&& oSystemUser.get().getPathImage().equals(request.getPathImage())
						&& oSystemUser.get().getRoleOriginSystemUser().equals(request.getRoleOriginSystemUser())
						&& oSystemUser.get().getGroupUser().equals(request.getGroupUser())
						&& oSystemUser.get().getDescription().equals(request.getDescription())) {
					return new FailResponse("Tài khoản người dùng chưa được sửa !");
				}
				SystemUser systemUser = oSystemUser.get();
				systemUser = MapUser.getSystemUserFromUpdateUSer(request, systemUser);
				systemUserRepo.save(systemUser);
				SystemUserDto dto = mapper.getDestination(systemUser);
				dto.setModifiedDate(LocalDateTime.now());
				return new ResponseObject<SystemUserDto>(dto);
			}
		} catch (Exception e) {
			return new FailResponse("Cập nhật tài khoản người dùng thất bại !");
		}

	}

}
