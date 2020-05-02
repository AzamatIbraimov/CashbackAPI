package kg.nurtelecom.cashbackapi.service;

import kg.nurtelecom.cashbackapi.entity.OrgBonusType;
import kg.nurtelecom.cashbackapi.model.OrgBonusTypeModel;

import java.util.List;

public interface OrgBonusTypeService {
    OrgBonusType findById(Long id);

    List<OrgBonusType> findAll();

    OrgBonusType create(OrgBonusType orgBonusType);

    String deleteById(Long id);

    OrgBonusType putById(Long id, OrgBonusType orgBonusType);

    List<OrgBonusTypeModel> findAllOrgBonusTypeDto();
}
