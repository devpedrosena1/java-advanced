package br.com.fiap.tds.javaadv.Library.presentation.transferObjects;

import br.com.fiap.tds.javaadv.Library.domainmodel.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
@Builder
public class UserDTO {

    private UUID id;

    @NotBlank(message = "Nome é obrigatorio, porra")
    @Size(max=100, message = "O nome deve ter no máximo 100 caracteres")
    private String name;

    @NotBlank(message = "Email é obrigatorio")
    @Email(message="O email deve ser válido")
    private String email;

    @NotBlank(message = "Password é obrigatorio")
    @Size(min=6, max=12, message="O password deve ter entre 6 e 12 caracteres")
    private String password;

//    private List<Post> posts;
//
//    private Profile profile;

    public static UserDTO fromEntity(User user) {
        if(user == null)
            return null;
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())

                .build();
    }

    public static User toEntity(UserDTO dto){
        if( dto == null )
            return null;
        return User.builder()
                .id(dto.id)
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword())

                .build();
    }

}
