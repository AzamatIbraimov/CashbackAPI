package kg.nurtelecom.cashbackapi.repository;

import kg.nurtelecom.cashbackapi.entity.User;
import kg.nurtelecom.cashbackapi.model.ManagerListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query("select new kg.nurtelecom.cashbackapi.model.ManagerListDto(user.id,user.username, user.organization.id , user.createdDate,user.roleNameShort) FROM User user  WHERE organization.id = :id")
    List<ManagerListDto> findManagersByOrgId(@Param("id") Long orgId);

    @Query("select new kg.nurtelecom.cashbackapi.model.ManagerListDto(user.id,user.username, user.organization.id, user.createdDate,user.roleNameShort) FROM User user  WHERE organization.id = :id")
    Page<ManagerListDto> findAllByOrgIdWithPagination(@Param("id") Long id, Pageable pageable);

    @Query("select new kg.nurtelecom.cashbackapi.model.ManagerListDto(user.id,user.username, user.organization.id, user.createdDate,user.roleNameShort) FROM User user  WHERE organization.id = :id and (lower(username) like %:search%) ORDER BY username ASC")
    Page<ManagerListDto> findAllByOrgIdAndByNameOrDescription(@Param("id") Long id, String search, Pageable pageable);
}
