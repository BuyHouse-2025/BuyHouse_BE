package com.ssafy.buyhouse.domain.board.domain;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Setter
    @Column(nullable = false)
    public String title;

    @Setter
    @Column(columnDefinition = "TEXT")
    public String content;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    public String creatTime;

//    @JoinColumn
//    @ManyToOne(fetch = FetchType.LAZY)
    // 회원 가입 후 변경
    @Column
    public String member;


}
