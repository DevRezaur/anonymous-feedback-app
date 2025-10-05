package com.devrezaur.main.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "feedback_table")
public class Feedback {
    @Id
    @GeneratedValue
    private UUID feedbackId;
    private String feedbackFor;
    private String feedbackBy;
    private String title;
    private String message;
    private boolean isPositive;
    private LocalDateTime createdAt;
}
