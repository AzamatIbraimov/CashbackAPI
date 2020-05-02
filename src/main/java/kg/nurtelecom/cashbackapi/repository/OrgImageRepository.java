package kg.nurtelecom.cashbackapi.repository;

import kg.nurtelecom.cashbackapi.entity.OrgImage;
import kg.nurtelecom.cashbackapi.model.OrgImageModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;
import java.util.List;

public interface OrgImageRepository extends JpaRepository<OrgImage, Long> {
    @Query("select new kg.nurtelecom.cashbackapi.model.OrgImageModel(orgImage.id, orgImage.organization, orgImage.image) FROM OrgImage orgImage WHERE organization_id = :id")
    Page<OrgImageModel> findAllByIdWithPagination(Long id, Pageable pageable);

    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
    @Query(value = "select new kg.nurtelecom.cashbackapi.model.OrgImageModel(orgImage.id, orgImage.organization, orgImage.image) FROM OrgImage orgImage WHERE organization_id = :orgId")
    List<OrgImageModel> getLastThreeImages(Long orgId, Pageable pageable);
}
