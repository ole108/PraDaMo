// Released under the terms of the Apache License, Version 2.0 or later.
// See the file 'COPYING' in the root directory for further information.
package controllers.fields;

import models.fields.DateField;
import play.mvc.With;
import controllers.CRUD;
import controllers.Check;
import controllers.Secure;


@Check("user")
@With(Secure.class)
@CRUD.For(DateField.class)
public class DateFields extends CRUD {

}
