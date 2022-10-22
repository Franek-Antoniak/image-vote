package com.picscore.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WebUserService {

	public String getUserNameId() {
		Authentication authentication = SecurityContextHolder.getContext()
		                                                     .getAuthentication();
		return authentication.getName();
	}
}
