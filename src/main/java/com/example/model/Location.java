package com.example.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	private String name;
	private String country;
	private String city;
	private String locationCode;
}
