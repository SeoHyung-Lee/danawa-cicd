package com.cicd.admin.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class ShowMainSiteDto {
    private long id;
    private String siteName;
    private String siteLink;
    private boolean showMain;
}
