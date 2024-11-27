package com.rocket.toucheese_be.domain.studio.product.entity;

import com.rocket.toucheese_be.domain.studio.review.entity.Review;
import com.rocket.toucheese_be.domain.studio.studio.entity.Studio;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Builder
@AllArgsConstructor(access = PROTECTED)
@NoArgsConstructor(access = PROTECTED)
@Getter
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "studio_id")
    private Studio studio;

    private String productName;

    @Column(length = 550) // 한글 약 180자
    private String description;

    private String productImage;

    private int productPrice;

    @Setter
    private int reviewCnt;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviewList = new ArrayList<>();

    private boolean groupFlag;

    private int basePeopleCnt;

    private int addPeoplePrice;

    private String productOptions;
    // productOptions -> String 같은 기본 객체는 List 필드 저장 불가
    // "1:name:price&2:name:price&3:name:price" 이런 식으로 저장, dto 보낼 땐 & 기준 split 하여 list 전송
    // 앱 단에서 `:` 기준 split 하여 사용 예정

    // 리뷰 추가 시 reviewCnt 증가
    public void reviewCntUp(Review review) {
        this.reviewList.add(review);
        this.reviewCnt = this.reviewList.size();
    }

    // 리뷰 삭제 시 reviewCnt 감소
    public void reviewCntDown(Review review) {
        this.reviewList.remove(review);
        this.reviewCnt = this.reviewList.size();
    }
}
