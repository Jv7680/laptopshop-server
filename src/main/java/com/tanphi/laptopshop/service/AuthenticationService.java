package com.tanphi.laptopshop.service;


import com.tanphi.laptopshop.entity.Accounts;
import com.tanphi.laptopshop.request.register.RegistrationRequest;
import com.tanphi.laptopshop.request.register.RegistrationRequestGoogleOauth;
import com.tanphi.laptopshop.security.oauth2.OAuth2UserInfo;

import java.util.Map;

public interface AuthenticationService {
    Map<String, String> login(String username);
    
    Map<String, String> loginggoauth(String username);

    void registerUser(RegistrationRequest request);

    boolean activateUser(String code);

    boolean sendPasswordResetCode(String email);

    Accounts findByPasswordResetCode(String code);

    String passwordReset(String email, String password);
    Accounts registerOauth2User(RegistrationRequestGoogleOauth request);

    Accounts updateOauth2User(Accounts accounts, String provider, OAuth2UserInfo oAuth2UserInfo);

	String updatePassword(Integer id, String password);
}
