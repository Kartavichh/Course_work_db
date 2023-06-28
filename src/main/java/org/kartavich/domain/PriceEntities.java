package org.kartavich.domain;

import javax.persistence.*;

@Entity @Table
public class PriceEntities {
    @Id @GeneratedValue
    public Integer ID;
    public Float data;
}