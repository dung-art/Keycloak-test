package com.WebOfNVD.User.Entity;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.WebOfNVD.Common.Convert.Convert;
import com.WebOfNVD.Common.Entity.EntityBase;
import com.googlecode.jmapper.annotations.JGlobalMap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JGlobalMap(excluded = { "MAXSIZE_CODE", "MINSIZE_CODE", "MINSIZE", "MAXSIZE", "isDelete", "userAdd", "dateAdd",
		"modifiedUser", "modifiedAction", "modifiedDate", "MINSIZE_DESCRIPTION", "MAXSIZE_DESCRIPTION",
		"MINSIZE_ROLE_AND_GROUP", "MINSIZE_IDENTYFY_NUMBER", "MAXSIZE_IDENTYFY_NUMBER" })
@Getter
@Setter
@Entity(name = "SystemUsers")
@Table(name = "qlbh_system_users")
@NoArgsConstructor
public class SystemUser extends EntityBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final static int MAXSIZE_CODE = 8;
	private final static int MINSIZE_CODE = 8;
	private final static int MINSIZE = 6;
//	private final static int MAXSIZE = 10;
	private final static int MINSIZE_ROLE_AND_GROUP = 2;
	private final static int MINSIZE_IDENTYFY_NUMBER = 9;
	private final static int MAXSIZE_IDENTYFY_NUMBER = 16;
	private final static int MINSIZE_DESCRIPTION = 50;
	private final static int MAXSIZE_DESCRIPTION = 6000;
//dinh dang user he thong : SYS+
	// 01 + : Admin
	// 02 + : Manager
	// 03 + : Supporter
	// .. + : khác
	@Id
	@Column(name = "System_User_Code", nullable = false, insertable = true)
	@NotNull
	@Size(min = MINSIZE_CODE, max = MAXSIZE_CODE)
	private String systemUserCode;

	@Column(name = "Full_Name", nullable = false, insertable = true)
	@NotNull
	@Size(min = MINSIZE)
	private String fullName;

	@Column(name = "User_Name", nullable = false, insertable = true)
	@NotNull
	@Size(min = MINSIZE)
	private String userName;

	@Column(name = "Identify_Number", nullable = false, insertable = true)
	@NotNull
	@Size(min = MINSIZE_IDENTYFY_NUMBER, max = MAXSIZE_IDENTYFY_NUMBER)
	private String identifyNumber;

	@Column(name = "Birth_Date", nullable = false, insertable = true)
	@NotNull
//	@Size(min = MINSIZE)
	private LocalDateTime birthDate;

	@Column(name = "Password", nullable = false, insertable = true)
	@NotNull
	@Size(min = MINSIZE)
	private String password;

	@Column(name = "Path_Image", nullable = false, insertable = true)
	@NotNull
	@Size(min = MINSIZE)
	private String pathImage;

	@Column(name = "Role_Origin_System_User", nullable = false, insertable = true)
	@NotNull
	@Size(min = MINSIZE_ROLE_AND_GROUP)
	private String roleOriginSystemUser;

	@Column(name = "Group_User", nullable = false, insertable = true)
	@NotNull
	@Size(min = MINSIZE_ROLE_AND_GROUP)
	private String groupUser;

	@Size(min = MINSIZE_DESCRIPTION, max = MAXSIZE_DESCRIPTION)
	@NotNull
	@Column(name = "description")
	private String description;

	// chi system user co role la Admin moi co the tao SystemUser
	public void setPassword(String password) throws NoSuchAlgorithmException {
		this.password = Convert.ConvertPassword(password);
	}
}
