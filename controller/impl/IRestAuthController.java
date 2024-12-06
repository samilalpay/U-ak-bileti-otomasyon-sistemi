package com.samilemir.controller.impl;

import com.samilemir.jwt.AuthRequest;
import com.samilemir.controller.RootEntity;
import com.samilemir.dto.DtoUser;

public interface IRestAuthController {
	
	public RootEntity<DtoUser> register(AuthRequest register);

}
