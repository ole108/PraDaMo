// Released under the terms of the Apache License, Version 2.0 or later.
// See the file 'COPYING' in the root directory for further information.
package models.fields;

import javax.persistence.Column;
import javax.persistence.Entity;

import models.RecordType;
import play.data.validation.Max;
import play.data.validation.Min;
import play.data.validation.Required;


@Entity
public class EmailField extends Field {
	@Required
	@Min(1)
	@Max(100)
	@Column(nullable = false)
	public Integer	maxLen;

	public EmailField(String fieldName, String displayName, RecordType entity, int maxLen) {
		super(fieldName, displayName, entity);

		this.maxLen = maxLen;
	}

}
