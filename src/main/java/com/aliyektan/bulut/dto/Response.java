package com.aliyektan.bulut.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Data
public class Response {
    private Integer httpStatus;
    private String errorDescription;
    private Object data;
}
