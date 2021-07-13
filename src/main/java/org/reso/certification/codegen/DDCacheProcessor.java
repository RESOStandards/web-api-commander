package org.reso.certification.codegen;

import org.reso.models.ReferenceStandardField;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DDCacheProcessor extends WorksheetProcessor {
  Map<String, List<ReferenceStandardField>> standardFieldCache = new LinkedHashMap<>();

  private void addToFieldCache(ReferenceStandardField field) {
    standardFieldCache.putIfAbsent(field.getParentResourceName(), new LinkedList<>());
    standardFieldCache.get(field.getParentResourceName()).add(field);
  }

  public Map<String, List<ReferenceStandardField>> getStandardFieldCache() {
    return standardFieldCache;
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
