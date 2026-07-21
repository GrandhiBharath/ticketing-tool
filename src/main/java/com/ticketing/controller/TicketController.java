package com.ticketing.controller;

import com.ticketing.model.*;
import com.ticketing.repository.*;
import com.ticketing.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "http://localhost:3000")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TechnicianRepository technicianRepository;

    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicket(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.getTicketById(id));
    }

    @PostMapping
    public ResponseEntity<Ticket> createTicket(
            @RequestParam("issueDescription") String issueDescription,
            @RequestParam("issueDetailDescription") String issueDetailDescription,
            @RequestParam("priority") String priority,
            @RequestParam("status") String status,
            @RequestParam("reportedById") Long reportedById,
            @RequestParam(value = "assignedToId", required = false) Long assignedToId,
            @RequestParam(value = "resolvedDate", required = false) String resolvedDate,
            @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {

        Ticket ticket = new Ticket();
        ticket.setIssueDescription(issueDescription);
        ticket.setIssueDetailDescription(issueDetailDescription);
        ticket.setPriority(Priority.valueOf(priority));
        ticket.setStatus(TicketStatus.valueOf(status));

        employeeRepository.findById(reportedById)
            .ifPresent(ticket::setReportedBy);

        if (assignedToId != null) {
            technicianRepository.findById(assignedToId)
                .ifPresent(ticket::setAssignedTo);
        }

        return ResponseEntity.ok(ticketService.createTicket(ticket, file));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> updateTicket(
            @PathVariable Long id,
            @RequestParam("issueDescription") String issueDescription,
            @RequestParam("issueDetailDescription") String issueDetailDescription,
            @RequestParam("priority") String priority,
            @RequestParam("status") String status,
            @RequestParam(value = "reportedById", required = false) Long reportedById,
            @RequestParam(value = "assignedToId", required = false) Long assignedToId,
            @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {

        Ticket updated = new Ticket();
        updated.setIssueDescription(issueDescription);
        updated.setIssueDetailDescription(issueDetailDescription);
        updated.setPriority(Priority.valueOf(priority));
        updated.setStatus(TicketStatus.valueOf(status));

        if (reportedById != null) {
            employeeRepository.findById(reportedById)
                .ifPresent(updated::setReportedBy);
        }
        if (assignedToId != null) {
            technicianRepository.findById(assignedToId)
                .ifPresent(updated::setAssignedTo);
        }

        return ResponseEntity.ok(ticketService.updateTicket(id, updated, file));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.ok().build();
    }
}
