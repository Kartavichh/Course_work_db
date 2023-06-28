package org.kartavich.domain;

import javax.persistence.*;

@Entity @Table
public class IphonesEntities {
    @Id @GeneratedValue
    public Integer ID;
    public String nameOfModel;
    public Float prise;

}