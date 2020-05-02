package kg.nurtelecom.cashbackapi.service;

import kg.nurtelecom.cashbackapi.entity.FilialRate;
import kg.nurtelecom.cashbackapi.model.FilialRateModel;

import java.util.List;

public interface FilialRateService {
    FilialRate findById(Long id);

    List<FilialRate> findAll();

    FilialRate create(FilialRate filialRate);

    String deleteById(Long id);

    FilialRate putById(Long id, FilialRate filialRate);

    List<FilialRateModel> findFilialRatesByFilialId(Long id);
}
