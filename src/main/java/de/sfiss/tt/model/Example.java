package de.sfiss.tt.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "example")
public class Example {
	@Id
	private String name;
}
