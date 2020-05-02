package kg.nurtelecom.cashbackapi.service;

import kg.nurtelecom.cashbackapi.entity.OrgImage;
import kg.nurtelecom.cashbackapi.model.OrgImageModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrgImageService {
    OrgImage findById(Long id);

//    List<OrgImage> findAll();

    OrgImage create(OrgImage orgImage);

    String deleteById(Long id);

    OrgImage putById(Long id, OrgImage orgImage);

    Page<OrgImageModel> findAllByOrgIdWithPagination(Long id, Pageable pageable);

    List<OrgImageModel> getLastThreeImages(Long orgId);
}
