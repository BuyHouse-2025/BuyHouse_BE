package com.ssafy.buyhouse.domain.auth.util;

public class JwtConstants {
    public static final String key = "나중에값넣기~~";
    public static final int ACCESS_EXP_TIME_MINUTES = 60;
    public static final int REFRESH_EXP_TIME_MINUTES = 60 * 48;
    public static final String JWT_HEADER = "Authorization";
    public static final String JWT_TYPE = "Bearer ";
    public static final String ACCESS = "accessToken";
    public static final String REFRESH = "refreshToken";
}