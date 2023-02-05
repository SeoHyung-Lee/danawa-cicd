package com.cicd.admin.entity;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "productCategory")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "deleted = false")
public class ProductCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "productId")
    private long productId;

    @OneToOne
    @JoinColumn(name = "categoryId")
    private CategoryEntity category;

    @Column(name = "deleted")
    private Boolean deleted;

}
