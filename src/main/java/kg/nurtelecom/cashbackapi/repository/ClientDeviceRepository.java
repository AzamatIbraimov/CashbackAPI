package kg.nurtelecom.cashbackapi.repository;

import kg.nurtelecom.cashbackapi.entity.ClientDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDeviceRepository extends JpaRepository<ClientDevice, Long> {
    ClientDevice findByPhoneNumber(String phone);
}
