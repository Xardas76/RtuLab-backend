package com.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Purchase {
    @Id
    @GeneratedValue
    private Long Id;
    private String name;
    private Date date;
    private Integer cost;

    Purchase() {}
}
