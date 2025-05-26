package com.ssafy.buyhouse.domain.board.domain;


import com.ssafy.buyhouse.domain.common.BaseTime;
import com.ssafy.buyhouse.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Setter
    @Column(nullable = false)
    public String title;

    @Setter
    @Column(columnDefinition = "TEXT")
    public String content;

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc")
    private List<Comment> comments;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    public Member member;



}
