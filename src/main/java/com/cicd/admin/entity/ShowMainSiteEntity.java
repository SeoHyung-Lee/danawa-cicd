package com.cicd.admin.entity;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "showMainSite")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "deleted = false")
public class ShowMainSiteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "siteName")
    private String siteName;

    @Column(name = "siteLink")
    private String siteLink;

    @Column(name = "show_main")
    private Boolean showMain;

    @Column(name = "deleted")
    private Boolean deleted;

}
