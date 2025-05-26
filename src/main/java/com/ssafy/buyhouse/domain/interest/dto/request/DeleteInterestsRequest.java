package com.ssafy.buyhouse.domain.interest.dto.request;

import java.util.List;

public record DeleteInterestsRequest (
    List<DeleteInterestRequest> interests
){}
