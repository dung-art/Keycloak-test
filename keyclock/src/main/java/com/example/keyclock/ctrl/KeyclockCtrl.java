package com.example.keyclock.ctrl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.keyclock.dto.InfoAccess;

@RestController
@RequestMapping
@Transactional
public class KeyclockCtrl {
	
	@PostMapping("/auth/realms/${realm}/protocol/openid-connect/token")
	public String token(
			@RequestParam(name = "realm",required = true) String realm,
			@RequestBody InfoAccess infoAccess) {
		String token = realm; 
		
		return token;		
	}
}
