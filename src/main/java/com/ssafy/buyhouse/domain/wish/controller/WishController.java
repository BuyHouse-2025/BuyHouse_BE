package com.ssafy.buyhouse.domain.wish.controller;

import com.ssafy.buyhouse.domain.auth.annotation.LoginUser;
import com.ssafy.buyhouse.domain.member.domain.Member;
import com.ssafy.buyhouse.domain.wish.dto.request.WishRequestDto;
import com.ssafy.buyhouse.domain.wish.dto.response.WishHouseResponseDto;
import com.ssafy.buyhouse.domain.wish.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/estate/wish")
public class WishController {
    private final WishService wishService;

    // 부동산 관심 설정 하기
    @PostMapping
    public ResponseEntity<?> addWish(@RequestBody WishRequestDto dto, @LoginUser Member member) {
        String result = wishService.registWishHouse(dto.aptSeq(), member); // member 설정후 변경
        return ResponseEntity.ok().body(result);
    }

    // 관심 부동산 조회 하기
    @GetMapping("")
    public ResponseEntity<List<WishHouseResponseDto>> GetWishHouse(@LoginUser Member member) {
        List<WishHouseResponseDto> wishHouseList = wishService.findAllWishHouse(member);
        return ResponseEntity.ok().body(wishHouseList);
    }

    // 관심 부동산 해제 하기
    @DeleteMapping("/{aptSeq}")
    public ResponseEntity<?> DeleteWishHouse(@LoginUser Member member, @PathVariable String aptSeq) {
        try{
            String result = wishService.delete(aptSeq, member);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
