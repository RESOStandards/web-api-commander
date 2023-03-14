package org.reso.certification.codegen;

import org.reso.models.ReferenceStandardField;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class DDCacheProcessor extends WorksheetProcessor {
  final AtomicReference<Map<String, Map<String, ReferenceStandardField>>> standardFieldCache =
      new AtomicReference<>(Collections.synchronizedMap(new LinkedHashMap<>()));

  private void addToFieldCache(ReferenceStandardField field) {
    standardFieldCache.get().putIfAbsent(field.getResourceName(), new LinkedHashMap<>());
    standardFieldCache.get().get(field.getResourceName()).put(field.getStandardName(), field);
  }

  public Map<String, Map<String, ReferenceStandardField>> getStandardFieldCache() {
    return standardFieldCache.get();
  }

  @Override
  void processNumber(ReferenceStandardField field) {
    addToFieldCache(field);
  }

  @Override
  void processStringListSingle(ReferenceStandardField field) {
    addToFieldCache(field);
  }

  @Override
  void processString(ReferenceStandardField field) {
    addToFieldCache(field);
  }

  @Override
  void processBoolean(ReferenceStandardField field) {
    addToFieldCache(field);
  }

  @Override
  void processStringListMulti(ReferenceStandardField field) {
    addToFieldCache(field);
  }

  @Override
  void processDate(ReferenceStandardField field) {
    addToFieldCache(field);
  }

  @Override
  void processTimestamp(ReferenceStandardField field) {
    addToFieldCache(field);
  }

  @Override
  void processCollection(ReferenceStandardField field) {
    addToFieldCache(field);
  }

  @Override
  void generateOutput() {
    //no output
  }
}
