package com.quantumsatis.userauthservice.service;

import com.quantumsatis.userauthservice.dto.UserDto;
import com.quantumsatis.userauthservice.exception.UserNotFoundException;
import com.quantumsatis.userauthservice.mapper.UserMapper;
import com.quantumsatis.userauthservice.repository.UserRepository;
import com.quantumsatis.userauthservice.repository.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public Page<UserDto> getUsers(Pageable pageable) {
        final Page<User> users = userRepository.findAll(pageable);
        final List<UserDto> userDtos = users.getContent()
                .stream()
                .map(userMapper::userToUserDto)
                .collect(Collectors.toList());

        return new PageImpl<>(userDtos, pageable, users.getTotalElements());
    }

    @Override
    public UserDto getUserById(Long id) {
        final User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            return userMapper.userToUserDto(user);
        }
        return null;
    }

    @Override
    public UserDto findByUsername(String username) {
        final User user = userRepository.findByUsername(username);
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto create(UserDto userDto) {
        final User user = userMapper.userDtoToUser(userDto);
        final User savedUser = userRepository.save(user);
        return userMapper.userToUserDto(savedUser);
    }

    @Override
    public UserDto update(Long id, UserDto userDto) throws UserNotFoundException {
        final User user = findUserInDatabase(id);
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setMiddleName(userDto.getMiddleName());
        final User savedUser = userRepository.save(user);
        return userMapper.userToUserDto(savedUser);
    }

    @Override
    public void delete(Long id) throws UserNotFoundException {
        final User user = findUserInDatabase(id);
        userRepository.delete(user);
    }

    private User findUserInDatabase(Long id) throws UserNotFoundException {
        final User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }

}
