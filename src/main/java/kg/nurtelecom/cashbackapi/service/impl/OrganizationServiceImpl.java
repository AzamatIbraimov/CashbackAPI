package kg.nurtelecom.cashbackapi.service.impl;

import kg.nurtelecom.cashbackapi.apicontroller.OrgCategoryRestController;
import kg.nurtelecom.cashbackapi.dao.OrganizationDao;
import kg.nurtelecom.cashbackapi.entity.OrgImage;
import kg.nurtelecom.cashbackapi.entity.Organization;
import kg.nurtelecom.cashbackapi.model.OrganizationModel;
import kg.nurtelecom.cashbackapi.model.OrganizationModelMultipartImage;
import kg.nurtelecom.cashbackapi.model.OrganizationShortModel;
import kg.nurtelecom.cashbackapi.repository.OrgImageRepository;
import kg.nurtelecom.cashbackapi.repository.OrganizationRepository;
import kg.nurtelecom.cashbackapi.service.OrganizationService;
import kg.nurtelecom.cashbackapi.utils.RecordNotFoundException;
import kg.nurtelecom.cashbackapi.utils.UtilBase64Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private OrgImageRepository orgImageRepository;

    @Autowired
    private OrganizationDao organizationDao;

    @Autowired
    private OrgCategoryRestController orgCategoryRestController;


    public Organization findById(Long id) {
        return organizationRepository.findById(id).orElseThrow(() ->
                new RecordNotFoundException("Record not found with id:" + id));
    }

    public List<Organization> findAll() {
        return organizationRepository.findAll();
    }

    public List<Organization> findAllSorted() {
        return organizationRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    @Override
    public Page<OrganizationModel> findAll(Pageable pageable) {
        return organizationRepository.findAllOrganizationListWithPagination(pageable);
    }

    @Override
    public Page<OrganizationModel> findAllByNameOrDescription(String search, Pageable pageable) {
        return organizationRepository.findAllByNameOrDescription(search, pageable);
    }

    @Override
    public Page<OrganizationModel> findRestOrganizationList(String search, Integer page, Integer size) {
        return organizationRepository.findAllByNameOrDescription(search, PageRequest.of(page, size));

    }

    public Organization create(OrganizationModelMultipartImage organizationModelMultipartImage) {
        Organization organization = new Organization();
        organization.setName(organizationModelMultipartImage.getName());
        Set<MultipartFile> multipartFiles = organizationModelMultipartImage.getImages();

        if(organizationModelMultipartImage.getImage() != null && organizationModelMultipartImage.getImage().getContentType().contains("image")) {
            organization.setImage(UtilBase64Image.encoder(organizationModelMultipartImage.getImage()));
            multipartFiles.add(organizationModelMultipartImage.getImage());
        }
        organization.setStatus(organizationModelMultipartImage.getStatus());
        organization.setOrgCategory(orgCategoryRestController.getOrgCategoryById(organizationModelMultipartImage.getCategoryId()));
        organization.setDescription(organizationModelMultipartImage.getDescription());
        Long id = organizationRepository.save(organization).getId();
        organization.setId(id);
        if(multipartFiles.size() > 0) {
            for (MultipartFile image : multipartFiles) {
                if (image.getContentType().contains("image")) {
                    OrgImage orgImage = new OrgImage();
                    orgImage.setImage(UtilBase64Image.encoder(image));
                    orgImage.setOrganization(organization);
                    orgImageRepository.save(orgImage);
                }
            }
        }
        return organization;
    }


    @Override
    public void changeStatus(Long id) {
        Organization organization = organizationRepository.findById(id).orElseThrow(() ->
                new RecordNotFoundException("Record not found with id:" + id));
        if (!organization.isStatus()){
            organization.setStatus(true);
        } else
            organization.setStatus(false);
        organizationRepository.save(organization);
    }

    public String deleteById(Long id) {
        organizationRepository.deleteById(id);
        return "Organization number " + id + " has been deleted!";
    }


//    public String deleteById(Long id) {
//        OrganizationModel organization = getOrganizationById(id);
//        ImageManager.deleteImage(organization.getImageUrl());
//        organizationRepository.deleteById(id);
//        return "Organization number " + id + " has been deleted!";
//    }

    public Organization putById(Long id, OrganizationModelMultipartImage organizationModelMultipartImage) {
        return organizationRepository.findById(id)
                .map(newOrganization -> {
                    newOrganization.setDescription(organizationModelMultipartImage.getDescription());
                    newOrganization.setName(organizationModelMultipartImage.getName());
                    if (organizationModelMultipartImage.getImage() != null && organizationModelMultipartImage.getImage().getContentType().contains("image")) {
                        newOrganization.setImage(UtilBase64Image.encoder(organizationModelMultipartImage.getImage()));
                        OrgImage orgImage = new OrgImage();
                        orgImage.setImage(UtilBase64Image.encoder(organizationModelMultipartImage.getImage()));
                        orgImage.setOrganization(newOrganization);
                        orgImageRepository.save(orgImage);
                    }
//                    newOrganization.setClients(organization.getClients());
//                    if (organizationModelImage.getStatus()==null)
//                        newOrganization.setStatus(false);
//                    else
//                        newOrganization.setStatus(true);
                    if (organizationModelMultipartImage.getStatus()!=null)
                        newOrganization.setStatus(true);
                    else
                        newOrganization.setStatus(false);

                    Long catId = organizationModelMultipartImage.getCategoryId();
                    if (catId != null) {
                        newOrganization.setOrgCategory(orgCategoryRestController.getOrgCategoryById(organizationModelMultipartImage.getCategoryId()));
                    }
                    return organizationRepository.save(newOrganization);
                })
                .orElseThrow(() ->
                        new RecordNotFoundException("Record not found with id:" + id));
    }
//    public Organization putById(Long id, Organization organization) {
//        return organizationRepository.findById(id)
//                .map(newOrganization -> {
//                    newOrganization.setDescription(organization.getDesription());
//                    newOrganization.setName(organization.getName());
//                    newOrganization.setClients(organization.getClients());
//                    newOrganization.setStatus(organization.getStatus());
//                    newOrganization.setImageUrl(organization.getImage());
//                    newOrganization.setOrgCategory(organization.getOrgCategory());
//                    return organizationRepository.save(newOrganization);
//                })
//                .orElseThrow(() ->
//                        new RecordNotFoundException("Record not found with id:" + id));
//    }


    public List<OrganizationModel> findAllOrganizationList() {
        return organizationRepository.findAllOrganizationList();
    }

    public List<OrganizationModel> getOrganizationListByClientId(Long id) {
        return organizationDao.getOrgByClientId(id);
    }

    @Override
    public List<OrganizationModel> nextOrganizationListByClientIdAndCategoryId(Long clientId, Long categoryId, Long lastId, Integer limit) {
        return organizationDao.getOrgByClientIdAndCategoryId(clientId, categoryId ,lastId, limit);
    }

    public OrganizationModel getOrganizationById(Long id) {
        return organizationRepository.getOrganizationById(id);
    }

    @Override
    public Page<OrganizationShortModel> findOrgsById(Long id, Integer page, Integer size) {
        return organizationRepository.findOrgsByCategoryId(id, PageRequest.of(page, size));
    }

    @Override
    public void changeLogo(Long orgId, Long imageId) {
        organizationRepository.findById(orgId)
                .map(newOrganization -> {
                    newOrganization.setImage(orgImageRepository.findById(imageId).get().getImage());
                    return organizationRepository.save(newOrganization);
                })
                .orElseThrow(() ->
                        new RecordNotFoundException("Record not found with id:" + orgId));
    }
}
