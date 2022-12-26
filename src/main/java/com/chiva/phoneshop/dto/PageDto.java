package com.chiva.phoneshop.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class PageDto {
    private List<?> list;

    private PaginationDto pageable;

    //    Use PageMapper instead
    //    public PageDto(Page<T> page) {
    //        this.list = page.getContent();
    //
    //        /*
    //        this.pageable = new PaginationDto();
    //        this.pageable.setEmpty(page.isEmpty());
    //        this.pageable.setFirst(page.isFirst());
    //        this.pageable.setLast(page.isLast());
    //        this.pageable.setPage(page.getNumber());
    //        this.pageable.setSize(page.getSize());
    //        this.pageable.setTotalElements(page.getTotalElements());
    //        this.pageable.setTotalPages(page.getTotalPages());
    //         */
    //
    //        /*
    //        use builder pattern
    //        this.pageable = PaginationDto.builder()
    //                .empty(page.isEmpty())
    //                .first(page.isFirst())
    //                .last(page.isLast())
    //                // .page(page.getNumber() + 1)
    //                .size(page.getSize())
    //                .totalElements(page.getTotalElements())
    //                .totalPages(page.getTotalPages())
    //                .build();
    //
    //        this.pageable.setPage(page.getNumber());
    //         */
    //    }
}
