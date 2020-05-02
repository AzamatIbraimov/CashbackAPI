package kg.nurtelecom.cashbackapi.controller;

import kg.nurtelecom.cashbackapi.entity.Event;
import kg.nurtelecom.cashbackapi.entity.Organization;
import kg.nurtelecom.cashbackapi.model.EventModel;
import kg.nurtelecom.cashbackapi.model.EventModelMultipartImage;
import kg.nurtelecom.cashbackapi.service.EventService;
import kg.nurtelecom.cashbackapi.service.OrganizationService;
import kg.nurtelecom.cashbackapi.service.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping({"organization/"})
public class EventController {
    @Autowired
    private EventService eventService;
    @Autowired
    private OrganizationService organizationService;

    @GetMapping(value = "{orgId}/event/list")
    public String getList(@PathVariable("orgId") Long orgId,
                          @RequestParam(value = "search", required = false) String search,
                          @PageableDefault(7) Pageable pageable,
                          Model model) {
        Page<EventModel> events;
        if (search != null) {
            events = eventService.findAllEventsByOrgIdAndName(orgId, search.toLowerCase(), pageable);
        } else {
            events = eventService.findAllEventsByOrgIdWithPagination(orgId, pageable);
        }
        boolean isEmpty = events.isEmpty();
        model.addAttribute("isEmpty", isEmpty);
        model.addAttribute("org_id", orgId);
        model.addAttribute("events", events);
        model.addAttribute("searchText", search);
        return "eventList";
    }

    @GetMapping(value = "event/list/forOrgAdmin")
    public String getListForOrgAdmin(
                          @RequestParam(value = "search", required = false) String search,
                          @PageableDefault(7) Pageable pageable,
                          Authentication authentication,
                          Model model) {
        UserPrincipal user = (UserPrincipal) authentication.getPrincipal();
        Organization organization = user.getOrganization();
        Page<EventModel> events;
        if (search != null) {
            events = eventService.findAllEventsByOrgIdAndName(organization.getId(), search.toLowerCase(), pageable);
        } else {
            events = eventService.findAllEventsByOrgIdWithPagination(organization.getId(), pageable);
        }
        boolean isEmpty = events.isEmpty();
        model.addAttribute("orgId", organization.getId());
        model.addAttribute("isEmpty", isEmpty);
        model.addAttribute("org_id", organization.getId());
        model.addAttribute("events", events);
        model.addAttribute("searchText", search);
        System.out.println(events.getContent().get(0).getDateFrom());
        System.out.println(events.getContent().get(0).getDateTo());
        return "eventList";
    }

    @GetMapping(value = "{orgId}/event/form")
    public String eventForm(Model model) {
        Event event = new Event();
        model.addAttribute("create", true);
        model.addAttribute("event", event);
        return "eventForm";
    }

    @GetMapping(value = "{orgId}/event/{eventId}")
    public String getEventDetailPage(@PathVariable("eventId") Long eventId, Model model) {
        Event event = eventService.findById(eventId);
        model.addAttribute("event", event);
        return "eventForm";
    }

    @PostMapping(value = "{orgId}/event/update/{eventId}")
    public String updateEvent(@PathVariable("eventId") Long eventId, @PathVariable("orgId") Long orgId, @Valid EventModelMultipartImage eventModel) {
        eventModel.setOrganization(organizationService.findById(orgId));
        eventService.putById(eventId, eventModel);
        return "redirect:/organization/" + orgId + "/event/list";
    }

    @GetMapping(value = "{orgId}/event/delete/{eventId}")
    public String deleteEvent(@PathVariable("eventId") Long eventId, @PathVariable("orgId") Long orgId) {
        eventService.deleteById(eventId);
        return "redirect:/organization/" + orgId + "/event/list";
    }

    @PostMapping(value = "{orgId}/event/create")
    public String createEvent(@PathVariable("orgId") Long orgId, @Valid EventModelMultipartImage event, BindingResult result) {
        event.setOrganization(organizationService.findById(orgId));
        if (result.hasErrors()) {
            return "eventForm";
        }
        eventService.create(event);
        return "redirect:/organization/" + orgId + "/event/list";
    }
}