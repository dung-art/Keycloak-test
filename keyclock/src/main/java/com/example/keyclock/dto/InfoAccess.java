package com.example.keyclock.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InfoAccess {
private String clientId;
private String username;
private String password;
private String grantType = "password";
}
