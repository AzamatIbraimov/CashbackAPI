package kg.nurtelecom.cashbackapi.service.impl;

import kg.nurtelecom.cashbackapi.apicontroller.OrganizationRestController;
import kg.nurtelecom.cashbackapi.apicontroller.RoleRestController;
import kg.nurtelecom.cashbackapi.entity.User;
import kg.nurtelecom.cashbackapi.model.ManagerListDto;
import kg.nurtelecom.cashbackapi.model.OrganizationManagerDto;
import kg.nurtelecom.cashbackapi.repository.UserRepository;
import kg.nurtelecom.cashbackapi.service.UserService;
import kg.nurtelecom.cashbackapi.utils.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrganizationRestController organizationRestController;

    @Autowired
    private RoleRestController roleRestController;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new RecordNotFoundException("Record not found with id:" + id));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public String deleteById(Long id) {
        userRepository.deleteById(id);
        return "User number " + id + " has been deleted!";
    }

    public User putById(Long id, User user) {
        return userRepository.findById(id)
                .map(newUser -> {
                    newUser.setOrganization(user.getOrganization());
                    newUser.setPassword(user.getPassword());
                    newUser.setStatus(user.getStatus());
                    newUser.setUsername(user.getUsername());
                    return userRepository.save(newUser);
                })
                .orElseThrow(() ->
                        new RecordNotFoundException("Record not found with id:" + id));
    }

    public User createOrganizationManager(OrganizationManagerDto organizationManagerDto, Boolean isManager) {
        User user = new User();
        user.setUsername(organizationManagerDto.getUsername());
        user.setPassword(passwordEncoder.encode(organizationManagerDto.getPassword()));
        user.setCreatedDate(Date.from(Instant.now()));
        user.setOrganization(organizationRestController.getOrganizationById(organizationManagerDto.getOrganizationId()));
        user.setStatus(true);
        if (!isManager) {
            user.setRoles(new HashSet<>(Collections.singleton(roleRestController.getRoleById((long) 3))));
            user.setRoleNameShort("cashier");
        } else {
            user.setRoles(new HashSet<>(Collections.singleton(roleRestController.getRoleById((long) 2))));
            user.setRoleNameShort("manager");
        }
        return userRepository.save(user);
    }


    @Override
    public User updateOrganizationManager(Long managerId, OrganizationManagerDto organizationManagerDto,Boolean isManager) {
        return userRepository.findById(managerId)
                .map(newUser -> {
//                    newUser.setId(organizationManagerDto.getUserId());
//                    newUser.setRoles(organizationManagerDto.getRoles());
                    newUser.setPassword(passwordEncoder.encode(organizationManagerDto.getPassword()));
                    newUser.setUsername(organizationManagerDto.getUsername());
                    return userRepository.save(newUser);
                })
                .orElseThrow(() ->
                        new RecordNotFoundException("Record not found with id:" + managerId));
    }

    @Override
    public List<ManagerListDto> findByOrgId(Long orgId) {
        return userRepository.findManagersByOrgId(orgId);
    }


    public Page<ManagerListDto> findAllByOrgIdAndByNameOrDescription(Long id, String search, Pageable pageable) {
        return userRepository.findAllByOrgIdAndByNameOrDescription(id, search, pageable);
    }

    public Page<ManagerListDto> findAllByOrgIdWithPagination(@Param("id") Long id, Pageable pageable) {
        return userRepository.findAllByOrgIdWithPagination(id, pageable);
    }
}

