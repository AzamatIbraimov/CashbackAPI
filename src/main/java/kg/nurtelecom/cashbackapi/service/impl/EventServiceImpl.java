package kg.nurtelecom.cashbackapi.service.impl;

import kg.nurtelecom.cashbackapi.dao.EventDao;
import kg.nurtelecom.cashbackapi.entity.Event;
import kg.nurtelecom.cashbackapi.model.EventFullModel;
import kg.nurtelecom.cashbackapi.model.EventModel;
import kg.nurtelecom.cashbackapi.model.EventModelMultipartImage;
import kg.nurtelecom.cashbackapi.repository.EventRepository;
import kg.nurtelecom.cashbackapi.service.EventService;
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
public class EventServiceImpl implements EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventDao eventDao;

    public Event findById(Long id) {
        return eventRepository.findById(id).orElseThrow(() ->
                new RecordNotFoundException("Record not found with id:" + id));
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public Event create(EventModelMultipartImage eventModelMultipartImage) {
        Event event = new Event();
        event.setId(eventModelMultipartImage.getId());
        event.setName(eventModelMultipartImage.getName());
        if(eventModelMultipartImage.getImage() != null && eventModelMultipartImage.getImage().getContentType().contains("image"))
            event.setImage(UtilBase64Image.encoder(eventModelMultipartImage.getImage()));
        event.setDescription(eventModelMultipartImage.getDescription());
        event.setDateFrom(eventModelMultipartImage.getDateFrom());
        event.setDateTo(eventModelMultipartImage.getDateTo());
        return eventRepository.save(event);
    }

    public String deleteById(Long id) {
        eventRepository.deleteById(id);
        return "Event number " + id + " has been deleted!";
    }

    public Event putById(Long id, EventModelMultipartImage event) {
        return eventRepository.findById(id)
                .map(newEvent -> {
                    if(event.getImage() != null && event.getImage().getContentType().contains("image"))
                        newEvent.setImage(UtilBase64Image.encoder(event.getImage()));
                    newEvent.setName(event.getName());
                    newEvent.setDescription(event.getDescription());
                    newEvent.setDateTo(event.getDateTo());
                    newEvent.setDateFrom(event.getDateFrom());
                    newEvent.setOrganization(event.getOrganization());
                    return eventRepository.save(newEvent);
                })
                .orElseThrow(() ->
                        new RecordNotFoundException("Record not found with id:" + id));
    }

    public List<EventModel> findAllEventsByOrgId(@Param("id") Long id) {
        return eventRepository.findAllEventsByOrgId(id);
    }

    public List<EventFullModel> getAllEventsByOrgId(Long orgId, Long lastId, String lastDate, Integer limit) {
        return eventDao.getEventByOrgId(orgId,lastId,lastDate,limit);
    }
    @Override
    public Page<EventModel> findAllEventsByOrgIdAndName(Long id, String search, Pageable pageable) {
        return eventRepository.findAllEventsByOrgIdAndName(id, search, pageable);
    }

    @Override
    public Page<EventFullModel> findRestAllEvents(Integer page, Integer size) {
        return eventRepository.findAllWhereDateFrom(PageRequest.of(page, size));
    }

    @Override
    public Page<EventModel> findAllEventsByOrgIdWithPagination(Long id, Pageable pageable) {
        return eventRepository.findAllEventsByOrgIdWithPagination(id, pageable);
    }

    @Override
    public Page<EventFullModel> findAllEventsByIdWithPagination(Long id, int page, int size) {
        return eventRepository.getAllEventsByOrgId(id, PageRequest.of(page, size));
    }


}
