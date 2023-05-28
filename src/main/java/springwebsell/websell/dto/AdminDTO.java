package springwebsell.websell.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdminDTO {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String repeatPassword;
}