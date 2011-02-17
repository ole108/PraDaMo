// Released under the terms of the Apache License, Version 2.0 or later.
// See the file 'COPYING' in the root directory for further information.
package controllers;

import models.SecUser;


/**
 * This view handles only simple security. It will be much more powerful when Apache Shiro is integrated.
 * 
 * @author ole
 */
public class Security extends controllers.Secure.Security {
	static boolean authenticate(String username, String password) {
		return SecUser.connect(username, password) != null;
	}

	static boolean check(String permission) {
		// TODO: Should be done with Apache Shiro really!
		if ("admin".equals(permission)) {
			return SecUser.find("byEmail", connected()).<SecUser> first().isAdmin;
		} else if ("user".equals(permission)) {
			return SecUser.find("byEmail", connected()) != null;
		}
		return false;
	}

	static void onAuthenticated() {
		CRUD.index();
	}

}
