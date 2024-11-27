package com.rocket.toucheese_be.domain.studio.review.dto;

import com.rocket.toucheese_be.domain.studio.review.entity.Reply;

import java.time.LocalDateTime;

public record ReplyDto (
        Long id,
        String studioName,
        LocalDateTime dateString, // 리부 작성 날짜
        String content
){
    public static ReplyDto from(Reply reply) {
        return new ReplyDto(
                reply.getId(),
                reply.getReview().getStudio().getName(),
                reply.getCreatedAt(),
                reply.getContent()
        );
    }
}

/*
*
struct Reply {
    let id: String
    let studioName: String
    let dateString: String
    let content: String
}
* */