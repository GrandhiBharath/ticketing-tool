package com.ticketing.service;

import com.ticketing.model.*;
import com.ticketing.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private EmployeeRepository EmployeeRepository;

    @Autowired
    private TechnicianRepository TechnicianRepository;

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id).orElseThrow();
    }

    public Ticket createTicket(Ticket ticket, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            String uploadDir = "uploads/";
            String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir + filename);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, file.getBytes());
            ticket.setAttachmentPath(filename);
        }
        ticket.setCreationDate(LocalDate.now());
        ticket.setStatus(TicketStatus.NEW);
        return ticketRepository.save(ticket);
    }

    public Ticket updateTicket(Long id, Ticket updated, MultipartFile file) throws IOException {
        Ticket ticket = ticketRepository.findById(id).orElseThrow();
        ticket.setIssueDescription(updated.getIssueDescription());
        ticket.setIssueDetailDescription(updated.getIssueDetailDescription());
        ticket.setPriority(updated.getPriority());
        ticket.setStatus(updated.getStatus());
        ticket.setAssignedTo(updated.getAssignedTo());
        ticket.setReportedBy(updated.getReportedBy());
        if (updated.getStatus() == TicketStatus.RESOLVED) {
            ticket.setResolvedDate(LocalDate.now());
        }
        if (file != null && !file.isEmpty()) {
            String uploadDir = "uploads/";
            String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir + filename);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, file.getBytes());
            ticket.setAttachmentPath(filename);
        }
        return ticketRepository.save(ticket);
    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }

    public List<Ticket> getTicketsByDateRange(LocalDate from, LocalDate to) {
        return ticketRepository.findByCreationDateBetween(from, to);
    }

    public List<Ticket> getResolvedTicketsByDateRange(LocalDate from, LocalDate to) {
        return ticketRepository.findByResolvedDateBetween(from, to);
    }
}