package kg.nurtelecom.cashbackapi.service.impl;

import kg.nurtelecom.cashbackapi.enums.OperationType;
import kg.nurtelecom.cashbackapi.model.*;
import kg.nurtelecom.cashbackapi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CashierServiceImpl implements CashierService {
    @Autowired
    private BalanceService balanceService;
    @Autowired
    private OrgBonusValueService orgBonusValueService;
    @Autowired
    private BalanceHistoryService balanceHistoryService;
    @Autowired
    private ClientService clientService;

    @Override
    public BonusValueModel getBonusValueByOrgIdAndTypeId(Long orgId, Long typeId) {
        return orgBonusValueService.getBonusValueByOrgIdAndTypeId(orgId, typeId);
    }

    @Override
    public boolean updateBalance(Double point, Long clientId, Long bonusId) {
        return balanceService.updateBalance(point , clientId, bonusId);
    }

    @Override
    public void insertBalanceHistory(Long orgId, Long userId,
                                     BalanceConfirmModel balanceConfirm,
                                     BonusValueModel bonusValueModel, OperationType operationType) {
        BalanceHistoryModel balanceHistoryModel = new BalanceHistoryModel();
        balanceHistoryModel.setOrgId(orgId);
        balanceHistoryModel.setUserId(userId);
        balanceHistoryModel.setBonusId(bonusValueModel.getBonusId());
        balanceHistoryModel.setClientId(balanceConfirm.getClientId());
        balanceHistoryModel.setAmount(balanceConfirm.getPoint());
        balanceHistoryModel.setBill(balanceConfirm.getNumberCheck());
        balanceHistoryModel.setOperationType(operationType);
        balanceHistoryModel.setInvoiceAmount(balanceConfirm.getInvoiceAmount());
        balanceHistoryService.create(balanceHistoryModel);
    }

    @Override
    public List<HistoryModel> findBalanceByClientAndOrgAndBonusTypeId(Long clientId, Long orgId, Long typeId) {
        return balanceService.findBalanceByClientAndOrgAndBonusTypeId(clientId, orgId, typeId);
    }

    @Override
    public void addBonusToBalance(Long orgId, Long userId, BalanceConfirmModel balanceConfirm, Integer bonusValue) {
        BonusValueModel bonusValueModel = getBonusValueByOrgIdAndTypeId(orgId, 3l);
        double cashAmount = - (balanceConfirm.getInvoiceAmount() - balanceConfirm.getPoint()) * bonusValue / 100;
        balanceConfirm.setPoint(-cashAmount);
        updateBalance(cashAmount , balanceConfirm.getClientId(), bonusValueModel.getBonusId());
        insertBalanceHistory(orgId, userId, balanceConfirm, bonusValueModel, OperationType.DEBIT);
    }

    @Override
    public List<ClientPreferenceModel> getClientPreferences(Long clientId, Long orgId) {
        return clientService.getClientPreferences(clientId, orgId);
    }

    @Override
    public Double getSumOfAmount(Long clientId, Long orgId) {
        List<BalanceModel> list = balanceService.getClientBalanceIdsByClientIdOrgId(clientId, orgId);
        Double sum = 0.0;
        for (BalanceModel b : list){
            sum += balanceHistoryService.getSumOfInvoiceAmount(b.getBalanceId());
        }
        return sum;
    }
}
