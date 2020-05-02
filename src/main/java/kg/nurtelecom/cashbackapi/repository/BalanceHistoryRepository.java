package kg.nurtelecom.cashbackapi.repository;

import kg.nurtelecom.cashbackapi.entity.BalanceHistory;
import kg.nurtelecom.cashbackapi.model.EventModel;
import kg.nurtelecom.cashbackapi.model.HistoryModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceHistoryRepository extends JpaRepository<BalanceHistory, Long> {

    @Query("select new kg.nurtelecom.cashbackapi.model.HistoryModel(history.id, balance.client.id, history.createdDate, history.operationType, history.amount, balance.amount, type.name, org.name) FROM BalanceHistory history JOIN Balance balance ON balance.id = history.balance.id JOIN OrgBonus bonus ON bonus.id = balance.orgBonus.id JOIN OrgBonusType type ON type.id = bonus.orgBonusType.id JOIN Organization org ON org.id = bonus.organization.id WHERE balance.client.id = :id")
    Page<HistoryModel> findBalanceHistoriesByClientId(Long id, Pageable pageable);

}
