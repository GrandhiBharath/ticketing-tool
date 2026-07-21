package com.ticketing.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketNumber;

    private LocalDate creationDate = LocalDate.now();

    @Column(length = 500)
    private String issueDescription;

    @Column(columnDefinition = "TEXT")
    private String issueDetailDescription;

    private String attachmentPath;

    @ManyToOne
    @JoinColumn(name = "reported_by")
    private Employee reportedBy;

    @ManyToOne
    @JoinColumn(name = "assigned_to")
    private Technician assignedTo;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    private LocalDate resolvedDate;

    @Enumerated(EnumType.STRING)
    private TicketStatus status = TicketStatus.NEW;

    // Getters and Setters
    public Long getTicketNumber() { return ticketNumber; }
    public void setTicketNumber(Long ticketNumber) { this.ticketNumber = ticketNumber; }
    public LocalDate getCreationDate() { return creationDate; }
    public void setCreationDate(LocalDate creationDate) { this.creationDate = creationDate; }
    public String getIssueDescription() { return issueDescription; }
    public void setIssueDescription(String issueDescription) { this.issueDescription = issueDescription; }
    public String getIssueDetailDescription() { return issueDetailDescription; }
    public void setIssueDetailDescription(String issueDetailDescription) { this.issueDetailDescription = issueDetailDescription; }
    public String getAttachmentPath() { return attachmentPath; }
    public void setAttachmentPath(String attachmentPath) { this.attachmentPath = attachmentPath; }
    public Employee getReportedBy() { return reportedBy; }
    public void setReportedBy(Employee reportedBy) { this.reportedBy = reportedBy; }
    public Technician getAssignedTo() { return assignedTo; }
    public void setAssignedTo(Technician assignedTo) { this.assignedTo = assignedTo; }
    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }
    public LocalDate getResolvedDate() { return resolvedDate; }
    public void setResolvedDate(LocalDate resolvedDate) { this.resolvedDate = resolvedDate; }
    public TicketStatus getStatus() { return status; }
    public void setStatus(TicketStatus status) { this.status = status; }
}