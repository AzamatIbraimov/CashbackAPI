package kg.nurtelecom.cashbackapi.service;

import kg.nurtelecom.cashbackapi.entity.Filial;
import kg.nurtelecom.cashbackapi.model.FilialModel;
import kg.nurtelecom.cashbackapi.model.FilialModelMultipartImage;
import kg.nurtelecom.cashbackapi.model.FilialShortModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FilialService {
    Filial findById(Long id);

    List<Filial> findAll();

    List<FilialModel> findAllByOrgId(Long id);

    Page<FilialModel> findAllByOrgIdWithPagination(Long id, Pageable pageable);

    Page<FilialModel> findAllByOrgIdAndByNameOrDescription(Long id, String search, Pageable pageable);

    Filial create(FilialModelMultipartImage filial);

    String deleteById(Long id);

    void changeStatus(Long id);


    Filial putById(Long id, FilialModelMultipartImage filial);

//    List<FilialModel> findAllFilialsByOrgId(@Param("id") Long id);

    List<FilialShortModel> getAllFilialsByOrgId(Long orgId, Long lastId, Double lastAverage, Integer limit);

    Page<FilialModel> getAllByOrgIdPagebale(Long id, Integer page, Integer size);
}