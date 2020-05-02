package kg.nurtelecom.cashbackapi.repository;

import kg.nurtelecom.cashbackapi.entity.OrgBonusType;
import kg.nurtelecom.cashbackapi.model.OrgBonusTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrgBonusTypeRepository extends JpaRepository<OrgBonusType, Long> {
    @Query("select new kg.nurtelecom.cashbackapi.model.OrgBonusTypeModel(orgBonusType.id, orgBonusType.name, orgBonusType.valueType,orgBonusType.description) FROM OrgBonusType orgBonusType")
    List<OrgBonusTypeModel> findAllOrgBonusTypeDto();
}