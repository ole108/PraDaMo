// Released under the terms of the Apache License, Version 2.0 or later.
// See the file 'COPYING' in the root directory for further information.
package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.data.validation.Match;
import play.data.validation.MaxSize;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.Model;


/**
 * Packages are used for organizing entities. Only one level is supported so far.
 * 
 * @author ole
 */
@Entity
public class Package extends Model {
	@Required
	@MinSize(value = 3)
	@MaxSize(96)
	@Match("[a-zA-Z0-9_]*")
	@Column(nullable = false, length = 96)
	public String	packageName;

	@Required
	@MinSize(value = 3)
	@MaxSize(64)
	@Column(nullable = false, length = 64)
	public String	displayName;

	@Required
	@ManyToOne(optional = false)
	public Project	project;

	public Package(String packageName, String displayName, Project project) {
		this.packageName = packageName;
		this.displayName = displayName;
		this.project = project;
	}

	@Override
	public String toString() {
		return displayName;
	}
}