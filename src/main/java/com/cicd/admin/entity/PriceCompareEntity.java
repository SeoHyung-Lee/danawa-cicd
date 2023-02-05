package com.cicd.admin.entity;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "priceCompare")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "deleted = false")
public class PriceCompareEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "productId")
    private long productId;

    @Column(name = "siteName")
    private String siteName;

    @Column(name = "siteLink")
    private String siteLink;

    @Column(name = "sitePrice")
    private long sitePrice;

    @Column(name = "deleted")
    private Boolean deleted;

}
