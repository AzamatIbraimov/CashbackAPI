package kg.nurtelecom.cashbackapi.service.impl;

import kg.nurtelecom.cashbackapi.entity.OrgImage;
import kg.nurtelecom.cashbackapi.model.OrgImageModel;
import kg.nurtelecom.cashbackapi.model.OrgMultipartImageModel;
import kg.nurtelecom.cashbackapi.repository.OrgImageRepository;
import kg.nurtelecom.cashbackapi.service.OrgImageService;
import kg.nurtelecom.cashbackapi.utils.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrgImageServiceImpl implements OrgImageService {

    @Autowired
    private OrgImageRepository orgImageRepository;

    @Override
    public OrgImage findById(Long id) {
        return orgImageRepository.findById(id).orElseThrow(() ->
                new RecordNotFoundException("Record not found with id:" + id));
    }
//
//    @Override
//    public List<OrgImage> findAll() {
//        return orgImageRepository.findAll();
//    }

    @Override
    public OrgImage create(OrgImage orgImage) {
        return orgImageRepository.save(orgImage);
    }

    @Override
    public String deleteById(Long id) {
        orgImageRepository.deleteById(id);
        return "Org Image number " + id + " has been deleted!";
    }

    @Override
    public OrgImage putById(Long id, OrgImage orgImage) {
        return null;
    }

    @Override
    public Page<OrgImageModel> findAllByOrgIdWithPagination(Long id, Pageable pageable) {
        return orgImageRepository.findAllByIdWithPagination(id, pageable);
    }

    @Override
    public List<OrgImageModel> getLastThreeImages(Long orgId) {
        Pageable paging = PageRequest.of(0, 3, Sort.by("id").descending());
        return orgImageRepository.getLastThreeImages(orgId, paging);
    }
}
