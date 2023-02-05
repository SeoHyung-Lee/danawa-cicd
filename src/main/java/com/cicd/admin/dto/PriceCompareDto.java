package com.cicd.admin.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class PriceCompareDto {

    private long id;
    private long productId;
    private String siteName;
    private String siteLink;
    private long sitePrice;

}
