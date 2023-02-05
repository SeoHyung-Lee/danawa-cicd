package com.cicd.admin.dto;

import com.cicd.admin.common.BreathingType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
public class MainProductDto {

    private long id;
    private String name;
    private String img;
    private long compareCnt;
    private long compareMinPrice;
    private BreathingType categoryOne;
}
