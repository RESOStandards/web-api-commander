package org.reso.models;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public final class IgnoredItem implements JsonDeserializer<IgnoredItem> {
  String lookup;
  String value;
  List<String> ignored;

  public IgnoredItem(String lookup, String value, List<String> ignored) {
    this.lookup = lookup;
    this.value = value;
    this.ignored = ignored;
  }

  public String getLookup() {
    return lookup;
  }

  public String getValue() {
    return value;
  }

  public List<String> getIgnored() {
    return ignored;
  }

  @Override
  public IgnoredItem deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    final String LOOKUP_KEY = "lookup", VALUE_KEY = "value", IGNORED_KEY = "ignored";

    JsonObject jsonObject = json.getAsJsonObject();
    String lookup = jsonObject.get(LOOKUP_KEY).getAsString();
    String value = jsonObject.get(VALUE_KEY).getAsString();
    List<String> ignored = new ArrayList<>();
    jsonObject.getAsJsonArray(IGNORED_KEY).iterator().forEachRemaining(item -> ignored.add(item.getAsString()));
    return new IgnoredItem(lookup, value, ignored);
  }
}
