package com.sce.app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Person Entity definition.
 *
 * @author julija.anna.lisaja@accenture.com
 */
@Entity(name = "Person")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private int age;
    private boolean isLegal;
}
