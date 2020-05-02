package kg.nurtelecom.cashbackapi.repository;

import kg.nurtelecom.cashbackapi.entity.Filial;
import kg.nurtelecom.cashbackapi.model.FilialModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilialRepository extends JpaRepository<Filial, Long> {
    @Query("select new kg.nurtelecom.cashbackapi.model.FilialModel(filial.id, filial.image, filial.status, filial.address, filial.name,filial.description, filial.latitude, filial.longitude, filial.organization.id, filial.averageRate) FROM Filial filial WHERE organization_id = :id  ORDER BY id ASC")
    List<FilialModel> findAllByOrgId(@Param("id") Long id);

    @Query("select new kg.nurtelecom.cashbackapi.model.FilialModel(filial.id, filial.image, filial.status, filial.address, filial.name,filial.description, filial.latitude, filial.longitude, filial.organization.id, filial.averageRate) FROM Filial filial WHERE organization_id = :id  ORDER BY id ASC")
    Page<FilialModel> findAllByOrgIdWithPagination(@Param("id") Long id, Pageable pageable);

    @Query("select new kg.nurtelecom.cashbackapi.model.FilialModel(filial.id, filial.image, filial.status, filial.address, filial.name,filial.description, filial.latitude, filial.longitude, filial.organization.id, filial.averageRate) FROM Filial filial WHERE organization_id = :id and (lower(name) like %:search% or lower(description) like %:search%) ORDER BY id ASC")
    Page<FilialModel> findAllByOrgIdAndByNameOrDescription(@Param("id") Long id, String search, Pageable pageable);
}
