package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "transportation")
public class Transportation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Enumerated(EnumType.STRING)
	private TransportationType type;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "from_id")
	private Location from;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "to_id")
	private Location to;
}
