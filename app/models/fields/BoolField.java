// Released under the terms of the Apache License, Version 2.0 or later.
// See the file 'COPYING' in the root directory for further information.
package models.fields;

import javax.persistence.Entity;

import models.RecordType;


@Entity
public class BoolField extends Field {
	public BoolField(String fieldName, String displayName, RecordType entity) {
		super(fieldName, displayName, entity);
	}
}