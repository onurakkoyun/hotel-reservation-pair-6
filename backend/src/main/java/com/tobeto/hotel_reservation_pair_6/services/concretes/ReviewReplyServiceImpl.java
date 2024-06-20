package com.tobeto.hotel_reservation_pair_6.services.concretes;

import com.tobeto.hotel_reservation_pair_6.core.results.Result;
import com.tobeto.hotel_reservation_pair_6.core.results.SuccessResult;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.ReviewReply;
import com.tobeto.hotel_reservation_pair_6.repositories.ReviewReplyRepository;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.ReviewReplyService;
import com.tobeto.hotel_reservation_pair_6.services.dtos.reviewReplyDtos.requests.AddReviewReplyRequest;
import com.tobeto.hotel_reservation_pair_6.services.mappers.ReviewReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ReviewReplyServiceImpl implements ReviewReplyService {

    private final ReviewReplyRepository replyReviewRepository;

    @Override
    public Result add(AddReviewReplyRequest request) {

        ReviewReply reviewReply = ReviewReplyMapper.INSTANCE.mapAddReviewReplyRequestToReviewReply(request);
        reviewReply.setCreationDate(LocalDateTime.now());
        replyReviewRepository.save(reviewReply);

        return new SuccessResult();
    }
}
