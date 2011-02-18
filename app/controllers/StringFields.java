// Released under the terms of the Apache License, Version 2.0 or later.
// See the file 'COPYING' in the root directory for further information.
package controllers;

import play.mvc.With;


@Check("user")
@With(Secure.class)
public class StringFields extends CRUD {

}
