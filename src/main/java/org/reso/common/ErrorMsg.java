package org.reso.common;

public final class ErrorMsg {
  public static final String ERROR_MESSAGE_TEMPLATE = "ERROR: %s";
  public static String formatErrorMessage(final String template, final String... msgs) {
    return String.format(template, (Object) msgs);
  }

  public static String getDefaultErrorMessage(final String msg) {
    return formatErrorMessage(ERROR_MESSAGE_TEMPLATE, msg);
  }

  public static String getDefaultErrorMessage(final Exception ex) {
    return formatErrorMessage(ex.toString());
  }
}