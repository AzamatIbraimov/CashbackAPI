package kg.nurtelecom.cashbackapi.service;

import kg.nurtelecom.cashbackapi.enums.OperationType;
import kg.nurtelecom.cashbackapi.model.BalanceConfirmModel;
import kg.nurtelecom.cashbackapi.model.BonusValueModel;
import kg.nurtelecom.cashbackapi.model.ClientPreferenceModel;
import kg.nurtelecom.cashbackapi.model.HistoryModel;

import java.util.List;

public interface CashierService {
    BonusValueModel getBonusValueByOrgIdAndTypeId(Long orgId, Long typeId);

    boolean updateBalance(Double point, Long clientId, Long bonusId);

    void insertBalanceHistory(Long orgId, Long userId, BalanceConfirmModel balanceConfirm, BonusValueModel bonusValueModel, OperationType operationType);

    List<HistoryModel> findBalanceByClientAndOrgAndBonusTypeId(Long clientId, Long orgId, Long typeId);

    void addBonusToBalance(Long orgId, Long userId, BalanceConfirmModel balanceConfirm, Integer bonusValue);

    List<ClientPreferenceModel> getClientPreferences(Long clientId, Long orgId);

    Double getSumOfAmount(Long clientId, Long orgId);
}
