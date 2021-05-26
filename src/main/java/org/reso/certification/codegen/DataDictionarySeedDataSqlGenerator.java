package org.reso.certification.codegen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reso.models.DataGenerator;
import org.reso.models.ReferenceStandardField;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 *
 * From: https://mariadb.com/kb/en/how-to-quickly-insert-data-into-mariadb/
 *
 * ALTER TABLE table_name DISABLE KEYS;
 * BEGIN;
 * ... inserting data with INSERT or LOAD DATA ....
 * COMMIT;
 * ALTER TABLE table_name ENABLE KEYS;
 *
 * SET @@session.unique_checks = 0;
 * SET @@session.foreign_key_checks = 0;
 *
 * SET @@global.innodb_autoinc_lock_mode = 2;
 *
 * Then use this to import the data:
 *
 * mysqlimport --use-threads=<numThreads> database text-file-name [text-file-name...]
 */
public class DataDictionarySeedDataSqlGenerator {
  private static final Logger LOG = LogManager.getLogger(DataDictionarySeedDataSqlGenerator.class);

  private final static AtomicReference<Map<String, Map<String, DataGenerator>>> dataGeneratorResourceFieldMap
      = new AtomicReference<>(Collections.synchronizedMap(new LinkedHashMap<>()));

  private final static AtomicReference<Map<String, List<ReferenceStandardField>>> referenceStandardFieldCache
      = new AtomicReference<>(Collections.synchronizedMap(new LinkedHashMap<>()));

  public DataDictionarySeedDataSqlGenerator() {
    referenceStandardFieldCache.set(DDCacheProcessor.getDDReferenceStandardFieldCache());
    dataGeneratorResourceFieldMap.set(DataGenerator.buildReferenceGeneratorCache());
  }

}
