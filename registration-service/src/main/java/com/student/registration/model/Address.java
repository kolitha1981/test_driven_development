package com.student.registration.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Address {
	
	@Column(name = "COUNTRY")
	private String country;
	@Column(name = "STATE")
	private String state;
	@Column(name = "PROVINCE")
	private String province;
	@Column(name = "STREET1")
	private String street1;
	@Column(name = "STREET2")
	private String street2;

}
