package kg.nurtelecom.cashbackapi.service.impl;

import kg.nurtelecom.cashbackapi.dao.BalanceDao;
import kg.nurtelecom.cashbackapi.entity.Balance;
import kg.nurtelecom.cashbackapi.model.BalanceModel;
import kg.nurtelecom.cashbackapi.model.HistoryModel;
import kg.nurtelecom.cashbackapi.repository.BalanceRepository;
import kg.nurtelecom.cashbackapi.service.BalanceService;
import kg.nurtelecom.cashbackapi.utils.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BalanceServiceImpl implements BalanceService {
    @Autowired
    private BalanceRepository balanceRepository;
    @Autowired
    private BalanceDao balanceDao;


    public Balance findById(Long id) {
        return balanceRepository.findById(id).orElseThrow(() ->
                new RecordNotFoundException("Record not found with id:" + id));
    }

    public List<Balance> findAll() {
        return balanceRepository.findAll();
    }

    public Balance create(Balance balance) {
        return balanceRepository.save(balance);
    }

    public String deleteById(Long id) {
        balanceRepository.deleteById(id);
        return "Balance number " + id + " has been deleted!";
    }

    public Balance putById(Long id, Balance balance) {
        return balanceRepository.findById(id)
                .map(newBalance -> {
                    newBalance.setAmount(balance.getAmount());
                    newBalance.setClient(balance.getClient());
                    newBalance.setOrgBonus(balance.getOrgBonus());
                    return balanceRepository.save(newBalance);
                })
                .orElseThrow(() ->
                        new RecordNotFoundException("Record not found with id:" + id));
    }

    @Override
    public List<HistoryModel> findBalanceByClientAndOrgAndBonusTypeId(Long clientId, Long orgId, Long typeId) {
        return balanceDao.getBalanceByClientIdAndOrgIdAndBonusTypeId(clientId, orgId, typeId);
    }

    @Override
    public List<BalanceModel> getClientBalanceByClientCodeOrgId(String code, Long orgId) {
        return balanceDao.getClientBalanceByClientCodeOrgId(code, orgId);
    }

    @Override
    public List<BalanceModel> getClientBalanceIdsByClientIdOrgId(Long clientId, Long orgId) {
        return balanceDao.getClientBalanceIdsByClientIdOrgId(clientId, orgId);
    }

    @Override
    public boolean updateBalance(Double amount, Long clientId, Long orgBonusId) {
        return balanceDao.updateBalance(amount, clientId, orgBonusId);
    }

}
