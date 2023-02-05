package com.cicd.admin.entity;

import com.cicd.admin.common.BreathingType;
import com.cicd.admin.common.CategoryType;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "category")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "deleted = false")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "categoryName")
    private String categoryName;

    @Column(name = "categoryOne")
    @Enumerated(EnumType.STRING)
    private BreathingType categoryOne;

    @Column(name = "categoryTwo")
    @Enumerated(EnumType.STRING)
    private CategoryType categoryTwo;

    @Column(name = "deleted")
    private Boolean deleted;

}
