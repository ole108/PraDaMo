// Released under the terms of the Apache License, Version 2.0 or later.
// See the file 'COPYING' in the root directory for further information.
package models.fields;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import models.RecordType;
import validation.BiggerThan;


@Entity
public class IntField extends Field {
	public Integer	minValue;

	@BiggerThan("minValue")
	public Integer	maxValue;

	@ManyToOne
	public IntField	smallerField	= null;

	public IntField(String fieldName, String displayName, RecordType entity) {
		super(fieldName, displayName, entity);
	}
}