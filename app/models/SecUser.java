// Released under the terms of the Apache License, Version 2.0 or later.
// See the file 'COPYING' in the root directory for further information.
package models;

import javax.persistence.Column;
import javax.persistence.Entity;

import play.data.validation.Email;
import play.data.validation.MaxSize;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.Model;


/**
 * A (somewhat) secure user class.
 * 
 * @author ole
 */
@Entity
public class SecUser extends Model {
	@Email
	@Required
	@Column(nullable = false, length = 100)
	public String	email;

	@Required
	@MinSize(value = 3)
	@MaxSize(32)
	@Column(nullable = false, length = 32)
	public String	passwordHash;

	@Required
	@MinSize(value = 3)
	@MaxSize(60)
	@Column(nullable = false, length = 60)
	public String	fullname;

	@Column(nullable = false)
	public boolean	isAdmin	= false;

	public SecUser(String email, String passwordHash, String fullname) {
		this.email = email;
		this.passwordHash = passwordHash;
		this.fullname = fullname;
	}

	public static SecUser connect(String email, String passwordHash) {
		// Crypto.passwordHash(passwordHash);
		return find("byEmailAndPasswordHash", email, passwordHash).first();
	}

	@Override
	public String toString() {
		return email;
	}
}
