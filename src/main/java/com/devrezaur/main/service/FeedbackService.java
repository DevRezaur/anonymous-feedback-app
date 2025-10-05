package com.devrezaur.main.service;

import com.devrezaur.main.model.Feedback;
import com.devrezaur.main.repository.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public Page<Feedback> getFeedbacks(Pageable pageable) {
        return feedbackRepository.findAll(pageable);
    }

    public List<Feedback> searchFeedbacksByName(String feedbackFor) {
        return feedbackRepository.findByFeedbackForContainingIgnoreCase(feedbackFor);
    }
}
