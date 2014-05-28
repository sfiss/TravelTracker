package de.sfiss.tt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "place")
@NamedQueries({ @NamedQuery(name = "Place.findAll", query = "SELECT p FROM Place p") })
public class Place extends EntityBase {
	// TODO: check if comparable interface
	@Column(name = "name", length = 255)
	@Size(max = 255)
	private String name;

	@Column(name = "location", length = 255, nullable = false)
	@NotNull
	@Size(min = 1, max = 255)
	private String location;

	public String getShortName() {
		return name == null || name.isEmpty() ? location : name;
	}
}
