package com.ssafy.buyhouse.domain.board.domain;


import com.ssafy.buyhouse.domain.common.BaseTime;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Setter
    @Column(nullable = false)
    public String title;

    @Setter
    @Column(columnDefinition = "TEXT")
    public String content;

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc")
    private List<Comment> comments;

//    @JoinColumn
//    @ManyToOne(fetch = FetchType.LAZY)
    // 회원 가입 후 변경
    @Column
    public String member;


}
