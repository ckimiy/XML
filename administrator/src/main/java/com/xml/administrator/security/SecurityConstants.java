package com.xml.administrator.security;

public class SecurityConstants {
	public static final String SECRET = "SecretKeyToGenJWTs";
	// 864_000_000 - 10 days
    public static final long EXPIRATION_TIME = 864_000_000; 
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users/sign-up";
    public static final String LOGIN_URL = "/admin/login";
    public static final String AUTHORITIES_KEY = "Authorities";
}
