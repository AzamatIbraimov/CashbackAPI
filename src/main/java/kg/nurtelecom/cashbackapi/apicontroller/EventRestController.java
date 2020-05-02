package kg.nurtelecom.cashbackapi.apicontroller;

import kg.nurtelecom.cashbackapi.entity.Event;
import kg.nurtelecom.cashbackapi.model.EventFullModel;
import kg.nurtelecom.cashbackapi.model.EventModel;
import kg.nurtelecom.cashbackapi.model.EventModelMultipartImage;
import kg.nurtelecom.cashbackapi.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/event")
public class EventRestController {
    @Autowired
    private EventService eventService;

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable("id") Long id) {
        return eventService.findById(id);
    }

//    @GetMapping("/all")
//    public List<Event> getAll() {
//        return eventService.findAll();
//    }

    @PutMapping("/{id}")
    public Event putEvent(@PathVariable("id") Long id, @RequestBody EventModelMultipartImage eventModel) {
        return eventService.putById(id, eventModel);
    }

    @PostMapping()
    public Event postEvent(@RequestBody EventModelMultipartImage eventModel) {
        return eventService.create(eventModel);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        return eventService.deleteById(id);
    }

    @GetMapping("/all")
    public List<EventModel> findAllEventsByOrgId(@PathVariable("id") Long id) {
        return eventService.findAllEventsByOrgId(id);
    }

    @GetMapping(value = "/all",
            params = {"page", "size" })
    public Page<EventFullModel> findAllEvents(@RequestParam("page") int page, @RequestParam("size") int size) {
        return eventService.findRestAllEvents(page, size);
    }
    @GetMapping(value = "/all/org/{id}",
            params = {"page", "size" })
    public Page<EventFullModel> findAllEvents(@PathVariable("id") Long id, @RequestParam("page") int page, @RequestParam("size") int size) {
        System.out.println("yo");
        return eventService.findAllEventsByIdWithPagination(id, page, size);
    }

    @GetMapping("/all/jdbc/{id}")
    public List<EventFullModel> getAllEventsByOrgId(@PathVariable("id") Long id,
                                                    @RequestParam(value = "last_i", defaultValue="0", required = false) Long eventId,
                                                    @RequestParam(value = "last_d", defaultValue = "2020-03-03 00:00:00", required = false) String lastDate,
                                                    @RequestParam(value = "limit", defaultValue = "5", required = false) Integer pageSize) {
        return eventService.getAllEventsByOrgId(id, eventId, lastDate, pageSize);
    }
}
