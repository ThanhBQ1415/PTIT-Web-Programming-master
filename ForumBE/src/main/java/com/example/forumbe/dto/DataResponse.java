package com.example.forumbe.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataResponse<T> {
    private T data;
    private int totalItems;

    public DataResponse() {
        totalItems = 0;
    }
}
