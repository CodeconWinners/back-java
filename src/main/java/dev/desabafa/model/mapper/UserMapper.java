package dev.desabafa.model.mapper;

import dev.desabafa.model.dto.UserDto;
import dev.desabafa.model.entity.UserProfile;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "meetings", ignore = true)
    UserProfile toEntity(UserDto dto);

    UserDto toDto(UserProfile entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "meetings", ignore = true)
    @Mapping(target = "userId", ignore = true)
    void updateFromDto(UserDto dto, @MappingTarget UserProfile entity);

}
