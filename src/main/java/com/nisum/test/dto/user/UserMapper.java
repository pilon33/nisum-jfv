package com.nisum.test.dto.user;

import com.nisum.test.dto.phone.PhoneMapper;
import com.nisum.test.model.User;
import org.mapstruct.*;
import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = PhoneMapper.class
)
public abstract class UserMapper {
    public abstract UserDto toDto(User user);

    public abstract User toModel(UserRequest userRequest);
    public abstract List<UserDto> toDto(List<User> users);
    public abstract void updateModel(UserRequest UserRequest, @MappingTarget User User);
    @Mapping(ignore = true, target = "phones")
    public abstract User updateModelFromUpdateRequest(UpdateUserRequest UserRequest, @MappingTarget User User);
}
