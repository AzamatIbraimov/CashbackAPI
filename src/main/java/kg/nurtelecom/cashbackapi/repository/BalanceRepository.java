package kg.nurtelecom.cashbackapi.repository;

import kg.nurtelecom.cashbackapi.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {
}
