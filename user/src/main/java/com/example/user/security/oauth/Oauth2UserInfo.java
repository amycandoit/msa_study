package com.example.user.security.oauth;


// Oauth 제공자마다 속성명이 달라서 내용 통일
public interface Oauth2UserInfo {

    String getProviderId();
    String getProvider();
    String getEmail();
    String getName();
    String profilePath();

}
