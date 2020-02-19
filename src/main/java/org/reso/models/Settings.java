package org.reso.models;

import java.io.File;
import java.util.*;

/**
 * The settings class contains all the settings a server can have, which currently means the following:
 * * client settings - named collection of server settings
 * * parameters - arbitrary collection of user-defined parameters
 * * requests - list of requests, one filter string per request, corresponding to a saved search
 */
public class  Settings {
  private ClientSettings clientSettings;
  private Parameters parameters;
  private Map<String, Request> requests;

  public Settings() {
    clientSettings = new ClientSettings();
    parameters = new Parameters();
    requests = new LinkedHashMap<>();
  }

  /**
   * Saves settings to the given filename.
   *
   * @param settings the settings to write.
   * @param filename the filename to save settings to.
   */
  public static void saveToRESOScript(Settings settings, String filename) { /* TODO */ ; }

  /**
   * Loads and returns settings from the given file.
   *
   * @param file the file to load settings from.
   * @return the settings contained in the file, or null. Prints stacktrace upon exception.
   */
  public static Settings loadFromRESOScript(File file) {
    Settings settings = new Settings();
    try {
      settings.setClientSettings(ClientSettings.loadFromRESOScript(file));
      settings.setParameters(Parameters.loadFromRESOScript(file));
      settings.setRequests(Request.loadFromRESOScript(file));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return settings;
  }

  static final String CLIENT_SETTING_PREFIX = "ClientSettings_";
  static final String PARAMETER_PREFIX = "Parameter_";

  /**
   * Resolves the parameters in request with parameters.
   *
   * @param request  the request to resolve.
   * @param settings the settings item containing the settings to resolve
   * @return a copy of the request with the URL resolved.
   */
  public static Request resolveParameters(Request request, Settings settings) {
    //calls to resolve nested parameters
    return new Request(request.getRequirementId(), request.getOutputFile(), resolveParametersString(request.getUrl(), settings), request.getTestDescription(),
        request.getMetallicLevel(), request.getCapability(), request.getWebApiReference(), request.getAssertResponseCode());
  }

  /**
   * Resolves URIs containing special RESOScript parameters of the form *Parameter_X* and *ClientSettings_Y*
   * @param parameterString the parameter string to resolve, possibly containing nested parameter settings
   * @param settings the settings to use to resolve the parameters
   * @return the resolved parameter string
   */
  private static String resolveParametersString(String parameterString, Settings settings) {
    StringBuilder resolved = new StringBuilder();
    String[] fragments = parameterString.split("\\*");
    String val;

    for (String fragment : fragments) {
      if (fragment.contains(CLIENT_SETTING_PREFIX)) {
        resolved.append(settings.getClientSettings().get(fragment.replace(CLIENT_SETTING_PREFIX, "")));
      } else if (fragment.contains(PARAMETER_PREFIX)) {
        val = settings.getParameters().getValue(fragment.replace(PARAMETER_PREFIX, ""));
        if (val != null) {
          if (val.contains(PARAMETER_PREFIX)) {
            resolved.append(resolveParametersString(val, settings));
          } else {
            resolved.append(val);
          }
        }
      } else {
        resolved.append(fragment);
      }
    }
    return resolved.toString();
  }

  /**
   * Client Settings getter.
   *
   * @return ClientSettings for this Settings Instance.
   */
  public ClientSettings getClientSettings() {
    return clientSettings;
  }

  /**
   * Client Settings setter.
   *
   * @param clientSettings sets the ClientSettings for the current Settings.
   */
  private void setClientSettings(ClientSettings clientSettings) {
    this.clientSettings = clientSettings;
  }

  /**
   * Parameters getter.
   *
   * @return Parameters for this Settings instance.
   */
  public Parameters getParameters() {
    return parameters;
  }

  /**
   * Parameters setter.
   *
   * @param parameters sets Parameters for the current Settings.
   */
  private void setParameters(Parameters parameters) {
    this.parameters = parameters;
  }

  /**
   * Requests getter.
   * @return The request map that was loaded, indexed by request name.
   */
  public Map<String, Request> getRequests() {
    return requests;
  }

  /**
   * Requests getter.
   *
   * @return returns server requests as a list
   */
  public List<Request> getRequestsAsList() {
    return new ArrayList<>(requests.values());
  }

  /**
   * Requests setter.
   *
   * @param requests a list of requests to create the request map from
   */
  private void setRequests(List<Request> requests) {
    this.requests = new LinkedHashMap<String, Request>();
    requests.forEach(request -> this.requests.put(request.getRequirementId(), request));
  }
}
