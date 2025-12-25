package crud.dto.mapper;

import crud.dto.UserDTO;
import crud.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toUserDTO(User user);

    List<UserDTO> toUserDTOs(List<User> users);

    User toUser(UserDTO userDTO);

    void updateUser(UserDTO userDTO, @MappingTarget User user);
}
