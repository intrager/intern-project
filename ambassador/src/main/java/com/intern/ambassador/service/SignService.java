package com.intern.ambassador.service;

import com.intern.ambassador.data.dto.SignInResultDto;
import com.intern.ambassador.data.dto.SignUpResultDto;

public interface SignService {
    SignUpResultDto signUp(String id, String password, String email, String name, int age, String phone, String role);
    SignInResultDto signIn(String id, String password) throws RuntimeException;
}