package com.airguitar.mapper;

import com.airguitar.model.User;
import com.airguitar.model.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDTO dto);

    UserDTO toDto(User entity);

}
