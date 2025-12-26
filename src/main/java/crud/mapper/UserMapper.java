package crud.mapper;

import crud.dto.UserDTO;
import crud.dto.UserRegistrationDTO;
import crud.dto.UserUpdateDTO;
import crud.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toUserDTO(User user);

    UserUpdateDTO toUserUpdateDTO(User user);

    List<UserDTO> toUserDTOs(List<User> users);

    User toUser(UserDTO userDTO);

    void updateUser(UserUpdateDTO userDTO, @MappingTarget User user);

    User toUserRegistration(UserRegistrationDTO userRegistrationDTO);

    UserRegistrationDTO toUserRegistrationDTO(User user);
}
