package com.ab.actionbook.domain;

import javax.persistence.*;

@Entity
@Table(name = "groups")
public class group {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;

}
