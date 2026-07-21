package com.ticketing.repository;

import com.ticketing.model.Ticket;
import com.ticketing.model.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByCreationDateBetween(LocalDate from, LocalDate to);
    List<Ticket> findByStatus(TicketStatus status);
    List<Ticket> findByResolvedDateBetween(LocalDate from, LocalDate to);
}
