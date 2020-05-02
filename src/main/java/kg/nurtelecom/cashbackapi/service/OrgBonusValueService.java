package kg.nurtelecom.cashbackapi.service;

import kg.nurtelecom.cashbackapi.entity.OrgBonusValue;
import kg.nurtelecom.cashbackapi.model.BonusValueModel;

import java.util.List;

public interface OrgBonusValueService {
    OrgBonusValue findById(Long id);

    List<OrgBonusValue> findAll();

    OrgBonusValue create(OrgBonusValue orgBonusValue);

    String deleteById(Long id);

    OrgBonusValue putById(Long id, OrgBonusValue orgBonusValue);

    BonusValueModel getBonusValueByOrgIdAndTypeId(Long orgId, Long typeId);
}
