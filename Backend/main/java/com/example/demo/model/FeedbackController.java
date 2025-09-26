package com.example.demo.model;

import com.example.demo.model.Feedback;
import com.example.demo.model.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin(origins = "http://localhost:3000") // React app URL
public class FeedbackController {

    @Autowired
    private FeedbackRepository feedbackRepository;

    // ✅ Get all feedback
    @GetMapping
    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    // ✅ Add new feedback
    @PostMapping
    public Feedback addFeedback(@RequestBody Feedback feedback) {
        // Default status = "Pending"
        if (feedback.getStatus() == null || feedback.getStatus().isEmpty()) {
            feedback.setStatus("Pending");
        }
        return feedbackRepository.save(feedback);
    }

    // ✅ Delete feedback by ID
    @DeleteMapping("/{id}")
    public String deleteFeedback(@PathVariable Long id) {
        if (!feedbackRepository.existsById(id)) {
            return "Feedback not found!";
        }
        feedbackRepository.deleteById(id);
        return "Feedback deleted successfully!";
    }

    // ✅ Mark as reviewed
    @PutMapping("/{id}/reviewed")
    public Feedback markAsReviewed(@PathVariable Long id) {
        Feedback fb = feedbackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feedback not found"));
        fb.setStatus("Reviewed");
        return feedbackRepository.save(fb);
    }
    
 // ✅ Update feedback status (Approve or Reject)
    @PutMapping("/{id}/status")
    public Feedback updateStatus(@PathVariable Long id, @RequestBody String status) {
        Feedback fb = feedbackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feedback not found"));
        fb.setStatus(status);
        return feedbackRepository.save(fb);
    }
}
