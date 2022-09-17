package com.intern.ambassador.service.impl;

import com.intern.ambassador.data.dto.ChangeUserInfoDto;
import com.intern.ambassador.data.dto.UserResponseDto;
import com.intern.ambassador.data.entity.User;
import com.intern.ambassador.data.repository.UserRepository;
import com.intern.ambassador.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    @Override
    public UserResponseDto getUserInfo(Long uno) {
        LOGGER.info("[getUserInfo] input number : {}", uno);
        User user = userRepository.findById(uno).get();
        LOGGER.info("[getUserInfo] input user : {}", user);
        UserResponseDto userResponseDto = getUserResponseDto(user);
        return userResponseDto;
    }

    @Override
    public List<UserResponseDto> getUserList() {
        LOGGER.info("[getUserList] 출력 시작");
        List<User> entityList = userRepository.findAll();
        List<UserResponseDto> dtoList = getUserListResponseDto(entityList);
        return dtoList;
    }



    @Override
    public UserResponseDto changeUserInfo(ChangeUserInfoDto changeUserInfoDto) throws Exception {
        User foundUser = userRepository.findById(changeUserInfoDto.getUno()).get();
        User changedUserInfo = userRepository.save(foundUser);
        UserResponseDto userResponseDto = getUserResponseDto(changedUserInfo);
        return userResponseDto;
    }

    @Override
    public void withdrawUser(Long id, String uid, String password) throws Exception {
        userRepository.deleteUserByIdAndUidAndPassword(id, uid, password);
    }
}
