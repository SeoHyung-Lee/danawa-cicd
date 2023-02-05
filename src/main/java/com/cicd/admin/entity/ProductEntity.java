package com.cicd.admin.entity;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "product")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "deleted = false")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "img", columnDefinition="BLOB")
    private byte[] img;

    @Column(name = "show_main")
    private Boolean showMain;

    @Column(name = "deleted")
    private Boolean deleted;

}
