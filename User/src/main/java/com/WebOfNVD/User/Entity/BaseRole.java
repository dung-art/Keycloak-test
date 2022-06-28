package com.WebOfNVD.User.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "base_role")
@Table(name = "qlbh_base_role")
@NoArgsConstructor
public class BaseRole implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static int MINSIZE = 2;
	private final static int MAXSIZE = 255;

	@Id
	@Column(name = "Id", nullable = false, insertable = true)
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Size(min = MINSIZE, max = MAXSIZE)
	private String id;

	@Column(name = "Role", nullable = false, insertable = true)
	@NotNull
	@Size(min = MINSIZE)
	private String role;

	public BaseRole(String role) {
		this.role = role;
	}
}
