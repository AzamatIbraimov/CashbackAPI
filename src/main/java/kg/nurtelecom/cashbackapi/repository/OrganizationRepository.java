package kg.nurtelecom.cashbackapi.repository;

import kg.nurtelecom.cashbackapi.entity.Organization;
import kg.nurtelecom.cashbackapi.model.OrganizationModel;
import kg.nurtelecom.cashbackapi.model.OrganizationShortModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    @Query("select new kg.nurtelecom.cashbackapi.model.OrganizationModel(organization.id, organization.image, organization.status, organization.name, orgCategory.id,orgCategory.name, organization.description) FROM Organization organization join OrgCategory orgCategory on organization.orgCategory = orgCategory.id")
    List<OrganizationModel> findAllOrganizationList();
    @Query("select new kg.nurtelecom.cashbackapi.model.OrganizationModel(organization.id, organization.image, organization.status, organization.name,orgCategory.id,orgCategory.name, organization.description) FROM Organization organization join OrgCategory orgCategory on organization.orgCategory = orgCategory.id where organization.id = :id")
    OrganizationModel getOrganizationById(Long id);

    @Query("select new kg.nurtelecom.cashbackapi.model.OrganizationModel(organization.id, organization.image, organization.status, organization.name,orgCategory.id,orgCategory.name, organization.description) FROM Organization organization join OrgCategory orgCategory on organization.orgCategory = orgCategory.id ORDER BY organization.name ASC")
    Page<OrganizationModel> findAllOrganizationListWithPagination(Pageable pageable);

    @Query("select new kg.nurtelecom.cashbackapi.model.OrganizationModel(organization.id, organization.image, organization.status, organization.name,orgCategory.id,orgCategory.name, organization.description) FROM Organization organization where lower(name) like %?1% or lower(description) like %?1% ORDER BY name ASC")
    Page<OrganizationModel> findAllByNameOrDescription(String search, Pageable pageable);

    @Query("select new kg.nurtelecom.cashbackapi.model.OrganizationShortModel(org.id, org.image, org.name, org.orgCategory.name) FROM Organization org WHERE org.orgCategory.id = :id")
    Page<OrganizationShortModel> findOrgsByCategoryId(Long id, Pageable pageable);
}

