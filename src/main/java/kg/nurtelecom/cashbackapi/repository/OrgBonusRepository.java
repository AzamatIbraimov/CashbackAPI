package kg.nurtelecom.cashbackapi.repository;

import kg.nurtelecom.cashbackapi.entity.OrgBonus;
import kg.nurtelecom.cashbackapi.model.BonusModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrgBonusRepository extends JpaRepository<OrgBonus, Long>, JpaSpecificationExecutor<BonusModel> {
    @Query("select new kg.nurtelecom.cashbackapi.model.BonusModel(orgBonus.id, orgBonus.orgBonusType, orgBonus.organization, orgBonus.status, orgBonus.validFrom, orgBonus.validTo, orgBonus.validity, orgBonus.createdDate) FROM OrgBonus orgBonus  ORDER BY orgBonus.id ASC")
    List<BonusModel> findAllOrgBonusList();

    @Query("select new kg.nurtelecom.cashbackapi.model.BonusModel(orgBonus.id, orgBonus.orgBonusType, orgBonus.organization, orgBonus.status, orgBonus.validFrom, orgBonus.validTo, orgBonus.validity, orgBonus.createdDate) FROM OrgBonus orgBonus  ORDER BY orgBonus.id ASC")
    Page<BonusModel> findAllOrgBonuses(Pageable pageable);

    @Query("select new kg.nurtelecom.cashbackapi.model.BonusModel(orgBonus.id, orgBonus.orgBonusType, orgBonus.organization, orgBonus.status, orgBonus.validFrom, orgBonus.validTo, orgBonus.validity, orgBonus.createdDate) FROM OrgBonus orgBonus WHERE organization_id = :id ORDER BY orgBonus.id ASC")
    Page<BonusModel> findByOrgIdWithPagination(Long id,Pageable pageable);

    @Query("select new kg.nurtelecom.cashbackapi.model.BonusModel(orgBonus.id, orgBonus.orgBonusType, orgBonus.organization, orgBonus.status, orgBonus.validFrom, orgBonus.validTo, orgBonus.validity, orgBonus.createdDate) FROM OrgBonus orgBonus where organization_id = :id  ORDER BY orgBonus.id ASC")
    List<BonusModel> findAllOrgBonusListByOrgId(@Param("id") Long id);
}
