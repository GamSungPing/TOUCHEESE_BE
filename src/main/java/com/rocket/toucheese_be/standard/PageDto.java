package com.rocket.toucheese_be.standard;

import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;

import java.util.List;

@Getter
public class PageDto<T> {
    @NonNull
    private long totalElementsCount;

    @NonNull
    private long pageElementsCount;

    @NonNull
    private long totalPagesCount;

    @NonNull
    private int pageNumber;

    @NonNull
    private List<T> studios;

    public PageDto(Page<T> page) {
        this.totalElementsCount = page.getTotalElements();
        this.pageElementsCount = page.getNumberOfElements();
        this.pageNumber = page.getNumber() + 1;
        this.studios = page.getContent();
        this.totalPagesCount = page.getTotalPages();
    }
}