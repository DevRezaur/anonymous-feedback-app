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
        Page<Feedback> page = feedbackRepository.findAll(pageable);

        return page.map(feedback -> {
            feedback.setMessage(truncateMessage(feedback.getMessage()));
            return feedback;
        });
    }

    public List<Feedback> searchFeedbacksByName(String feedbackFor) {
        List<Feedback> feedbacks = feedbackRepository.findByFeedbackForContainingIgnoreCase(feedbackFor);
        feedbacks.forEach(feedback -> feedback.setMessage(truncateMessage(feedback.getMessage())));

        return feedbacks;
    }

    private String truncateMessage(String message) {
        if (message != null && message.length() > 200) {
            return message.substring(0, 200) + " …";
        } else {
            return message;
        }
    }
}
