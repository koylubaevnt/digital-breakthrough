package com.quantumsatis.userauthservice.service;

import com.quantumsatis.userauthservice.dto.UserDto;
import com.quantumsatis.userauthservice.exception.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<UserDto> getUsers(Pageable pageable);

    UserDto findByUsername(String username);

    UserDto create(UserDto userDto);

    UserDto update(Long id, UserDto userDto) throws UserNotFoundException;

    void delete(Long id) throws UserNotFoundException;

    UserDto getUserById(Long id);
}
