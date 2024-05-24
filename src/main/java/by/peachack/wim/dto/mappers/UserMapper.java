package by.peachack.wim.dto.mappers;

import by.peachack.wim.dto.UserDTO;
import by.peachack.wim.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper{
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "password", ignore = true),
            @Mapping(target = "login", source = "username")
    })
    UserDTO userToUserDTO(User user);
    @Mapping(target = "username", source = "login")
    User userDTOToUser(UserDTO userDTO);
}
