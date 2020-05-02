package kg.nurtelecom.cashbackapi.service.impl;

import kg.nurtelecom.cashbackapi.entity.FilialRate;
import kg.nurtelecom.cashbackapi.model.FilialRateModel;
import kg.nurtelecom.cashbackapi.repository.FilialRateRepository;
import kg.nurtelecom.cashbackapi.service.FilialRateService;
import kg.nurtelecom.cashbackapi.utils.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilialRateServiceImpl implements FilialRateService {
    @Autowired
    private FilialRateRepository filialRateRepository;

    public FilialRate findById(Long id) {
        return filialRateRepository.findById(id).orElseThrow(() ->
                new RecordNotFoundException("Record not found with id:" + id));
    }

    public List<FilialRate> findAll() {
        return filialRateRepository.findAll();
    }

    public FilialRate create(FilialRate filialRate) {
        return filialRateRepository.save(filialRate);
    }

    public String deleteById(Long id) {
        filialRateRepository.deleteById(id);
        return "FilialRate number " + id + " has been deleted!";
    }

    public FilialRate putById(Long id, FilialRate filialRate) {
        return filialRateRepository.findById(id)
                .map(newFilialRate -> {
                    newFilialRate.setClient(filialRate.getClient());
                    newFilialRate.setComment(filialRate.getComment());
                    newFilialRate.setFilial(filialRate.getFilial());
                    newFilialRate.setOrganization(filialRate.getOrganization());
                    newFilialRate.setRate(filialRate.getRate());
                    return filialRateRepository.save(newFilialRate);
                })
                .orElseThrow(() ->
                        new RecordNotFoundException("Record not found with id:" + id));
    }

    public List<FilialRateModel> findFilialRatesByFilialId(Long id) {
        return filialRateRepository.findAllFilialRatesByFilialId(id);
    }
}
