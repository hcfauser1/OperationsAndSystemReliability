
package gov.hhs.cms.pricer.v9109.exceptions;

import java.util.ArrayList;

public class DateValidationException extends Exception {

  private static final long serialVersionUID = 1L;
  private ArrayList<String> invalid;  //added to avoid compile warning

  public DateValidationException(String message, ArrayList<String> invalid) {
    super(message);
    this.invalid = invalid;
  }

  public ArrayList<String> getInvalid() {
    return this.invalid;
  }
}
