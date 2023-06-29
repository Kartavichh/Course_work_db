package org.kartavich.domain;

import javax.persistence.*;

@Entity @Table
public class IphonesEntities {
    @Id @GeneratedValue
    public Integer ID;
    public String nameOfModel;
    public Float prise;

    public IphonesEntities(Float prise, String nameOfModel) {
        this.prise = prise;
        this.nameOfModel = nameOfModel;
    }
    public IphonesEntities() {

    }
}