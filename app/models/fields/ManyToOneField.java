// Released under the terms of the Apache License, Version 2.0 or later.
// See the file 'COPYING' in the root directory for further information.
package models.fields;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import models.RecordType;
import play.data.validation.Match;
import play.data.validation.MaxSize;
import play.data.validation.MinSize;
import play.data.validation.Required;


@Entity
public class ManyToOneField extends Field {
	@Required
	@ManyToOne(optional = false)
	public RecordType	fieldType;

	@MinSize(value = 3)
	@MaxSize(32)
	@Match("[a-zA-Z0-9_]*")
	@Column(length = 32)
	public String		nameInOtherEntity;

	public ManyToOneField(String fieldName, String displayName, RecordType entity, RecordType fieldType) {
		super(fieldName, displayName, entity);

		this.fieldType = fieldType;
	}
}
