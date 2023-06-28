package org.kartavich.domain;

import javax.persistence.*;

@Entity @Table
public class MyData {
    @Id @GeneratedValue
    public Integer ID;
    public String data;
}