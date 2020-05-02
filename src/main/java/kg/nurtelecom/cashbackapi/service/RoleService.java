package kg.nurtelecom.cashbackapi.service;

import kg.nurtelecom.cashbackapi.entity.Role;

import java.util.List;

public interface RoleService {
    Role findById(Long id);

    List<Role> findAll();

    Role create(Role role);

    String deleteById(Long id);

    Role putById(Long id, Role role);
}
