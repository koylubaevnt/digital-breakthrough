package com.quantumsatis.userauthservice.mapper;

import com.quantumsatis.userauthservice.dto.UserDto;
import com.quantumsatis.userauthservice.repository.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {


    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto dto);
}