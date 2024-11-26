package com.rocket.toucheese_be.domain.studio.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Getter
@RequiredArgsConstructor
public enum ConceptMainUrl {
    ONE_FLASH(1L, "플래쉬", "https://i.pinimg.com/736x/f9/54/34/f954340904ec93acfe71f3e77395b757.jpg"),
    TWO(2L, "생동감", "https://recsofficial.cafe24.com/web/upload/NNEditor/20230112/EC9784ECA780EC9CA4_EC8580EB9FACEBB88CEBA6ACED8BB0.jpg"),
    THREE(3L, "블루블랙", "https://i.imgur.com/aXwN6fF.jpeg"),
    FOUR(4L, "내추럴", "https://i.pinimg.com/736x/d2/59/7c/d2597c06b76ad83921e5c1ead54565ba.jpg"),
    FIVE(5L, "수채화", "https://i.imgur.com/xxBwQVX.jpeg"),
    SIX(6L, "선명인형", "https://i.imgur.com/0c2YvpR.jpeg");

    private final Long conceptId;
    private final String conceptName;
    private final String mainUrl;

    public static ConceptMainUrl findById(Long id) {
        for (ConceptMainUrl url : ConceptMainUrl.values()) {
            if (Objects.equals(url.getConceptId(), id)) {
                return url;
            }
        }
        return null;
    }
}

