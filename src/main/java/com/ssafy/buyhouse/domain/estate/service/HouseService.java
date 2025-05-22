package com.ssafy.buyhouse.domain.estate.service;

import com.ssafy.buyhouse.domain.estate.domain.HouseInfo;
import com.ssafy.buyhouse.domain.estate.dto.request.SearchRequestDto;
import com.ssafy.buyhouse.domain.estate.dto.response.HouseResponseDto;
import com.ssafy.buyhouse.domain.estate.repository.HouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class HouseService {

    private final HouseRepository houseRepository;

    public List<HouseResponseDto> searchName(SearchRequestDto searchRequestDto) {
        System.out.println(searchRequestDto.getAptNm());
        System.out.println(searchRequestDto.getMinPrice());
        System.out.println(searchRequestDto.getMaxPrice());
        System.out.println(searchRequestDto.getMinSquare());
        System.out.println(searchRequestDto.getMaxSquare());

        List<HouseInfo> houseinfos =  houseRepository.findBySearchInfo(
                searchRequestDto.getAptNm(),
                searchRequestDto.getMinPrice(),
                searchRequestDto.getMaxPrice(),
                searchRequestDto.getMinSquare(),
                searchRequestDto.getMaxSquare()
                );

        return houseinfos.stream().map(HouseResponseDto::from)
                .collect(Collectors.toList());
    }
}
