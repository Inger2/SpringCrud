package crud.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record UserDTO(Long id, @NotNull @Length(min = 2, max = 20) String name,
                      @NotNull
                      @Email
                      @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]" +
                              "+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$") String email) {

}
