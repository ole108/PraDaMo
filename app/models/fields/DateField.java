// Released under the terms of the Apache License, Version 2.0 or later.
// See the file 'COPYING' in the root directory for further information.
package models.fields;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import models.RecordType;
import play.data.validation.Required;
import validation.BiggerThan;


@Entity
public class DateField extends Field {
	@Required
	@Column(nullable = false)
	public TemporalType		dateType;

	@Required
	@Column(nullable = false)
	public DateRelativeType	relativeToInput	= DateRelativeType.NOTHING;

	@Temporal(TemporalType.TIMESTAMP)
	public Date				afterValue;

	@BiggerThan("afterValue")
	@Temporal(TemporalType.TIMESTAMP)
	public Date				beforeValue;

	public DateField(String fieldName, String displayName, RecordType entity) {
		super(fieldName, displayName, entity);

	}
}