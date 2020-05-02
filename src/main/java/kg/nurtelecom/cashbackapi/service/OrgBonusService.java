package kg.nurtelecom.cashbackapi.service;

import kg.nurtelecom.cashbackapi.entity.OrgBonus;
import kg.nurtelecom.cashbackapi.model.BonusLongModel;
import kg.nurtelecom.cashbackapi.model.BonusModel;
import kg.nurtelecom.cashbackapi.model.BonusShort;
import kg.nurtelecom.cashbackapi.model.BonusShortModel;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

public interface OrgBonusService {
    OrgBonus findById(Long id);

    List<OrgBonus> findAll();


    Page<BonusLongModel> findAllPageable(Integer page, Integer size);

    OrgBonus create(OrgBonus orgBonus);

    String deleteById(Long id);

    void changeStatus(Long id);

    OrgBonus putById(Long id, OrgBonus orgBonus);

    List<BonusModel> findAllOrgBonusList();

    List<BonusModel> findAllOrgBonusListByOrgId(Long id);

    List<BonusShortModel> getAllOrgBonusListByOrgId(Long orgId, Integer lastValidity, Long lastId, Integer limit);

    List<BonusModel> findByCriteria(Long orgId, Long bonusTypeId, Date validFrom, Date validTo, Boolean status);

    Page<BonusLongModel> findByIdPageable(Long id, Integer page, Integer size);
}
