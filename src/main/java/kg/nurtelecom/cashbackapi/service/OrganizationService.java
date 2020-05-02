package kg.nurtelecom.cashbackapi.service;

import kg.nurtelecom.cashbackapi.entity.Organization;
import kg.nurtelecom.cashbackapi.model.OrganizationModel;
import kg.nurtelecom.cashbackapi.model.OrganizationModelMultipartImage;
import kg.nurtelecom.cashbackapi.model.OrganizationShortModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrganizationService {

    Organization findById(Long id);

    List<Organization> findAll();

    List<Organization> findAllSorted();

    Page<OrganizationModel> findAll(Pageable pageable);

    Page<OrganizationModel> findAllByNameOrDescription(String search, Pageable pageable);

    Page<OrganizationModel> findRestOrganizationList(String search, Integer page, Integer size);


    Organization create(OrganizationModelMultipartImage organization);

    void changeStatus(Long id);

    String deleteById(Long id);

    Organization putById(Long id, OrganizationModelMultipartImage organization);

    List<OrganizationModel> findAllOrganizationList();


    List<OrganizationModel> getOrganizationListByClientId(Long id);

    List<OrganizationModel> nextOrganizationListByClientIdAndCategoryId(Long clientId, Long categoryId, Long lastId, Integer limit);

    OrganizationModel getOrganizationById(Long id);

    void changeLogo(Long orgId, Long imageId);

    Page<OrganizationShortModel> findOrgsById(Long id, Integer page, Integer size);
}
