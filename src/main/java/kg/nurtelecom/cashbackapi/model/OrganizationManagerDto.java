package kg.nurtelecom.cashbackapi.model;

import kg.nurtelecom.cashbackapi.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationManagerDto {
    private Long id;
    @Size(min = 5, max = 255)
    @NotNull
    private String username;
    @Size(min = 6, max = 255)
    @NotNull
    private String password;
    private Set<Role> roles;
    private Long organizationId;
}