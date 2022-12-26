package com.chiva.phoneshop.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
@Builder
public class PaginationDto {
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean empty;
    private boolean first;
    private boolean last;
    public void setPage(int page) {
        this.page = page + 1;
    }
}
