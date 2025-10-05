package com.devrezaur.main.controller;

import com.devrezaur.main.model.Feedback;
import com.devrezaur.main.repository.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
public class AppController {

    private final int PAGE_SIZE = 5;
    private final FeedbackRepository feedbackRepository;

    @GetMapping({"/", "/home"})
    public String homePage(@RequestParam(defaultValue = "0") int page, Model model) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE, Sort.by("createdAt").descending());
        Page<Feedback> feedbackPage = feedbackRepository.findAll(pageable);

        model.addAttribute("feedbacks", feedbackPage.getContent());
        model.addAttribute("totalPages", feedbackPage.getTotalPages());
        model.addAttribute("currentPage", page);

        return "home-page";
    }

    @GetMapping("/search")
    public String searchFeedbacks(@RequestParam String searchQuery, Model model) {
        List<Feedback> feedbacks = feedbackRepository.findByFeedbackForContainingIgnoreCase(searchQuery);
        model.addAttribute("feedbacks", feedbacks);
        model.addAttribute("searchQuery", searchQuery);
        return "home-page";
    }

    @GetMapping("/feedback/{feedbackId}")
    public String feedbackDetail(@PathVariable UUID feedbackId) {
        return "feedback-page";
    }
}
