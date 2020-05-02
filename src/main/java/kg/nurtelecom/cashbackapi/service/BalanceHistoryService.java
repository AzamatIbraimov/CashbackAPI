package kg.nurtelecom.cashbackapi.service;

import kg.nurtelecom.cashbackapi.entity.BalanceHistory;
import kg.nurtelecom.cashbackapi.model.BalanceHistoryLongModel;
import kg.nurtelecom.cashbackapi.model.BalanceHistoryModel;
import kg.nurtelecom.cashbackapi.model.HistoryModel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BalanceHistoryService {
    BalanceHistory findById(Long id);

    List<BalanceHistory> findAll();

//    BalanceHistory create(BalanceHistory BalanceHistory);
    BalanceHistory create(BalanceHistoryModel balanceHistoryModel);

    String deleteById(Long id);

    BalanceHistory putById(Long id, BalanceHistory BalanceHistory);

    Page<HistoryModel> getHistoryByClientIdPageable(Long clientId, Integer page, Integer size);

    List<HistoryModel> findHistoryByClientId(Long clientId, String last, Long historyId, Integer pageSize);

    List<HistoryModel> findHistoryByClientAndOrgAndBonusTypeId(Long clientId, Long orgId, Long typeId);

    List<BalanceHistoryLongModel> getCashierOperationHistory(Long userId, String dateFrom, String dateTo);

    Double getSumOfInvoiceAmount(Long balance_id);
}
