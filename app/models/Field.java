// Released under the terms of the Apache License, Version 2.0 or later.
// See the file 'COPYING' in the root directory for further information.
package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import play.data.validation.Match;
import play.data.validation.MaxSize;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.Model;


/**
 * @author ole
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Field extends Model {
	@Required
	@MinSize(3)
	@MaxSize(32)
	@Match("[a-zA-Z0-9_]*")
	@Column(nullable = false, length = 32)
	public String		fieldName;

	@Required
	@MinSize(3)
	@MaxSize(64)
	@Column(nullable = false, length = 64)
	public String		displayName;

	@Required
	@ManyToOne(optional = false)
	public RecordType	entity;

	@Column(nullable = false)
	public boolean		required	= false;

	public Field(String fieldName, String displayName, RecordType entity) {
		this.fieldName = fieldName;
		this.displayName = displayName;
		this.entity = entity;
	}

	public String toString() {
		return displayName;
	}
}