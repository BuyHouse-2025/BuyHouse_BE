package com.ssafy.buyhouse.domain.interest.dto.response;

import java.util.List;

public record InterestsResponse(
        List<InterestResponse> interests
) {
}
