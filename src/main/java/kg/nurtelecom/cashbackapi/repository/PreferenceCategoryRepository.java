package kg.nurtelecom.cashbackapi.repository;

import kg.nurtelecom.cashbackapi.entity.PreferenceCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferenceCategoryRepository extends JpaRepository<PreferenceCategory, Long> {
}
