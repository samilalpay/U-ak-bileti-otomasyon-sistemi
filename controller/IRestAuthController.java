package com.samilemir.controller;

import com.samilemir.dto.DtoUser;
import com.samilemir.jwt.AuthRequest;

public interface IRestAuthController {
	public RootEntity <DtoUser> register(AuthRequest request);

}
