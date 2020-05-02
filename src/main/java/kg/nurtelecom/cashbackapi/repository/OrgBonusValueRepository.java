package kg.nurtelecom.cashbackapi.repository;

import kg.nurtelecom.cashbackapi.entity.OrgBonusValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrgBonusValueRepository extends JpaRepository<OrgBonusValue, Long> {
}
