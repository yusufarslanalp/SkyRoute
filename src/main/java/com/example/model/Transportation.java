package com.example.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Builder
@Table(name = "transportaion")
public class Transportation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private TransportationType type;
	@ManyToOne
	private Location from;
	@ManyToOne
	private Location to;
}
