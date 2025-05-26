package com.ssafy.buyhouse.domain.interest.dto.response;

import com.ssafy.buyhouse.domain.interest.domain.Interest;

import java.util.List;
import java.util.stream.Collectors;

public record InterestsResponse(
        List<InterestResponse> interests
) {
    public static InterestsResponse from(List<Interest> interests) {
        List<InterestResponse> interestResponses = interests.stream()
                .map(InterestResponse::from)
                .collect(Collectors.toList());

        return new InterestsResponse(interestResponses);
    }

}
