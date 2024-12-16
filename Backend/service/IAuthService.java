package com.samilemir.service;

import com.samilemir.dto.DtoUser;
import com.samilemir.jwt.AuthRequest;

public interface IAuthService {
	public DtoUser register(AuthRequest request);
}
