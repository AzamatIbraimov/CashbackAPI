package kg.nurtelecom.cashbackapi.service.impl;

import kg.nurtelecom.cashbackapi.apicontroller.OrganizationRestController;
import kg.nurtelecom.cashbackapi.dao.FilialDao;
import kg.nurtelecom.cashbackapi.entity.Filial;
import kg.nurtelecom.cashbackapi.model.FilialModel;
import kg.nurtelecom.cashbackapi.model.FilialModelMultipartImage;
import kg.nurtelecom.cashbackapi.model.FilialShortModel;
import kg.nurtelecom.cashbackapi.repository.FilialRepository;
import kg.nurtelecom.cashbackapi.service.FilialService;
import kg.nurtelecom.cashbackapi.utils.RecordNotFoundException;
import kg.nurtelecom.cashbackapi.utils.UtilBase64Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilialServiceImpl implements FilialService {
    @Autowired
    private FilialRepository filialRepository;

    @Autowired
    private FilialDao filialDao;

    @Autowired
    private OrganizationRestController organizationRestController;

    public Filial findById(Long id) {
        return filialRepository.findById(id).orElseThrow(() ->
                new RecordNotFoundException("Record not found with id:" + id));
    }

    public List<Filial> findAll() {
        return filialRepository.findAll();
    }


    public Filial create(FilialModelMultipartImage filialModel) {
        Filial filial = new Filial();
        filial.setOrganization(organizationRestController.getOrganizationById(filialModel.getOrgId()));
        if(filialModel.getImage() != null && filialModel.getImage().getContentType().contains("image"))
            filial.setImage(UtilBase64Image.encoder(filialModel.getImage()));
        filial.setAddress(filialModel.getAddress());
        filial.setName(filialModel.getName());
        filial.setDescription(filialModel.getDescription());
        filial.setLatitude(filialModel.getLatitude());
        filial.setLongitude(filialModel.getLongitude());
        return filialRepository.save(filial);
    }

    public String deleteById(Long id) {
        filialRepository.deleteById(id);
        return "Filial number " + id + " has been deleted!";
    }



    @Override
    public void changeStatus(Long id) {
        Filial filial = filialRepository.findById(id).orElseThrow(() ->
                new RecordNotFoundException("Record not found with id:" + id));
        if (!filial.getStatus()){
            filial.setStatus(true);
        } else
            filial.setStatus(false);
        filialRepository.save(filial);
    }


//    public Filial putById(Long id, Filial filial) {
//        return filialRepository.findById(id)
//                .map(newFilial -> {
//                    newFilial.setDescription(filial.getDescription());
//                    newFilial.setName(filial.getName());
//                    newFilial.setAddress(filial.getAddress());
//                    newFilial.setAverageRate(filial.getAverageRate());
//                    newFilial.setLatitude(filial.getLatitude());
//                    newFilial.setLongitude(filial.getLongitude());
//                    newFilial.setStatus(filial.getStatus());
//                    newFilial.setOrganization(filial.getOrganization());
//                    return filialRepository.save(newFilial);
//                })
//                .orElseThrow(() ->
//                        new RecordNotFoundException("Record not found with id:" + id));
//    }
    public Filial putById(Long id, FilialModelMultipartImage filial) {
        return filialRepository.findById(id)
                .map(newFilial -> {
                    newFilial.setDescription(filial.getDescription());
                    if(filial.getImage() != null && filial.getImage().getContentType().contains("image"))
                        newFilial.setImage(UtilBase64Image.encoder(filial.getImage()));
                    newFilial.setName(filial.getName());
                    newFilial.setAddress(filial.getAddress());
                    newFilial.setLatitude(filial.getLatitude());
                    newFilial.setAverageRate(filial.getAverageRate());
                    newFilial.setLongitude(filial.getLongitude());
                    newFilial.setStatus(filial.getStatus());
                    return filialRepository.save(newFilial);
                })
                .orElseThrow(() ->
                        new RecordNotFoundException("Record not found with id:" + id));
    }

//    @Override
//    public List<FilialModel> findAllFilialsByOrgId(Long id) {
//        return null;
//    }

    public Page<FilialModel> findAllByOrgIdAndByNameOrDescription(Long id, String search, Pageable pageable) {
        return filialRepository.findAllByOrgIdAndByNameOrDescription(id, search, pageable);
    }

    public Page<FilialModel> findAllByOrgIdWithPagination(@Param("id") Long id, Pageable pageable) {
        return filialRepository.findAllByOrgIdWithPagination(id, pageable);
    }

    public List<FilialModel> findAllByOrgId(@Param("id") Long id) {
        return filialRepository.findAllByOrgId(id);
    }

    public List<FilialShortModel> getAllFilialsByOrgId(Long orgId, Long lastId, Double lastAverage, Integer limit) {
        return filialDao.getFilialByOrgId(orgId,lastId, lastAverage,limit);
    }

    @Override
    public Page<FilialModel> getAllByOrgIdPagebale(Long id, Integer page, Integer size) {
        return filialRepository.findAllByOrgIdWithPagination(id, PageRequest.of(page, size));
    }
}
