package com.manufacture.identityservice.mappers;

import com.manufacture.identityservice.dto.UserResponseDTO;
import com.manufacture.identityservice.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        UserMapper.class})
public interface UserMapper {

    UserResponseDTO userDAOToUserDTO(User user);
}
