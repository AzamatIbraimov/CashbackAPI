package kg.nurtelecom.cashbackapi.repository;

import kg.nurtelecom.cashbackapi.entity.ClientEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientEventRepository extends JpaRepository<ClientEvent, Long> {
}
