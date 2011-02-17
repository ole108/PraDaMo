// Released under the terms of the Apache License, Version 2.0 or later.
// See the file 'COPYING' in the root directory for further information.
package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
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
public class RecordType extends Model {
	@Required
	@MinSize(value = 3)
	@MaxSize(32)
	@Match("[a-zA-Z0-9_]*")
	@Column(nullable = false, length = 32)
	public String	entityName;

	@Required
	@MinSize(value = 3)
	@MaxSize(64)
	@Column(nullable = false, length = 64)
	public String	displayName;

	@Lob
	@MaxSize(10000)
	public String	description;

	@ManyToOne
	public Package	parentPackage;

	@Required
	@ManyToOne(optional = false)
	public Project	project;

	public RecordType(String entityName, String displayName, String description, Package parentPackage, Project project) {
		this.entityName = entityName;
		this.displayName = displayName;
		this.description = description;
		this.parentPackage = parentPackage;
		this.project = project;
	}

	@Override
	public String toString() {
		return displayName;
	}
}
