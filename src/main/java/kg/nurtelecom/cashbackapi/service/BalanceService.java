package kg.nurtelecom.cashbackapi.service;

import kg.nurtelecom.cashbackapi.entity.Balance;
import kg.nurtelecom.cashbackapi.model.BalanceModel;
import kg.nurtelecom.cashbackapi.model.HistoryModel;

import java.util.List;

public interface BalanceService {
    Balance findById(Long id);

    List<Balance> findAll();

    Balance create(Balance balance);

    String deleteById(Long id);

    Balance putById(Long id, Balance balance);

    List<HistoryModel> findBalanceByClientAndOrgAndBonusTypeId(Long clientId, Long orgId, Long typeId);

    List<BalanceModel> getClientBalanceByClientCodeOrgId(String code, Long orgId);

    List<BalanceModel> getClientBalanceIdsByClientIdOrgId(Long clientId, Long orgId);

    boolean updateBalance(Double amount,Long clientId, Long orgBonusId);
}
