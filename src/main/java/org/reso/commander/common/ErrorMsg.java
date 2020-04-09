package org.reso.commander.common;

import static org.reso.commander.certfication.containers.WebAPITestContainer.SINGLE_SPACE;

public final class ErrorMsg {
  private static final String ERROR_MESSAGE_TEMPLATE = "ERROR: %s";
  private static final String ASSERTED_RESPONSE_CODE_TEMPLATE = "Asserted response code (%d) does not match the one returned from the server (%d)!";

  /**
   * Formats the given items with the default error message
   * @param msgs the list of messages to display
   * @return a string containing the default error message for display
   */
  public static String getDefaultErrorMessage(final String... msgs) {
    return String.format(ERROR_MESSAGE_TEMPLATE, String.join(SINGLE_SPACE, msgs));
  }

  /**
   * Formats error message using the default template
   * @param ex the exception whose toString() method should be called
   * @return formatted error message using the default format
   */
  public static String getDefaultErrorMessage(final Exception ex) {
    return getDefaultErrorMessage(ex.toString());
  }

  /**
   * Specialized Assert code formatter
   * @param assertedResponseCode the response code that was asserted
   * @param serverResponseCode the server response code that was returned
   * @return the formatted error message for this case
   */
  public static String getAssertResponseCodeErrorMessage(final Integer assertedResponseCode, final Integer serverResponseCode) {
    return String.format(ASSERTED_RESPONSE_CODE_TEMPLATE, assertedResponseCode, serverResponseCode);
  }
}