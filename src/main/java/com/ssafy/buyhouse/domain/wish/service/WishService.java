package com.ssafy.buyhouse.domain.wish.service;

import com.ssafy.buyhouse.domain.estate.domain.HouseInfo;
import com.ssafy.buyhouse.domain.estate.repository.HouseRepository;
import com.ssafy.buyhouse.domain.member.domain.Member;
import com.ssafy.buyhouse.domain.wish.domain.WishHouse;
import com.ssafy.buyhouse.domain.wish.dto.response.WishHouseResponseDto;
import com.ssafy.buyhouse.domain.wish.repository.WishRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class WishService {
    private final WishRepository wishRepository;
    private final HouseRepository houseRepository;

    // 부동산 관심 설정 하기
    public String registWishHouse(String aptSeq, Member member) {
        HouseInfo houseInfo = houseRepository.findById(aptSeq)
                .orElseThrow(() -> new EntityNotFoundException("단지 정보가 없습니다. aptSeq=" + aptSeq));

        WishHouse wishHouse = WishHouse.builder()
                .member(member)
                .houseInfo(houseInfo)
                .build();
        wishRepository.save(wishHouse);
        return "부동산 관심설정이 완료 되었습니다.";
    }

    // 관심 부동산 조회 하기
    public List<WishHouseResponseDto> findAllWishHouse(Member member) {
        // 멤버 생성후 수정
//        Member member = memberRepository.findById(userId)
//                .orElseThrow(() -> new EntityNotFoundException("회원이 없습니다. id=" + userId));

        List<WishHouse> wishHouses = wishRepository.findAllByMember(member);

        return wishHouses.stream()
                .map(WishHouseResponseDto::from)
                .collect(Collectors.toList());
    }

    // 관심 부동산 해제 하기
    public String delete(Long id, String memberId) throws Exception {
        WishHouse wishHose = wishRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("관심 부동산 목록이 없습니다. id=" + id));

        if(!wishHose.getMember().getId().equals(memberId))
            throw new IllegalAccessException("해당 사용자의 관심 부동산이 아닙니다.");

        wishRepository.delete(wishHose);
        return "관심 부동산 해제가 완료되었습니다.";
    }
}
