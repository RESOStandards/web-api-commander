package org.reso.certification.testgenerators;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.Objects;

public class DataDictionaryBDDGenerator {
  String dataDictionaryReference = null;

  public DataDictionaryBDDGenerator() {
    dataDictionaryReference = DDReferenceVersion.v1_7;
  }

  public void readDictionaryReference() {
    try {
      Workbook workbook = new XSSFWorkbook(OPCPackage.open(Objects.requireNonNull(Thread.currentThread().getContextClassLoader()
              .getResourceAsStream(dataDictionaryReference))));
      Sheet datatypeSheet = workbook.getSheetAt(0);

      for (Row currentRow : datatypeSheet) {

        for (Cell currentCell : currentRow) {
          if (currentCell.getCellType() == Cell.CELL_TYPE_STRING) {
            System.out.print(currentCell.getStringCellValue() + "--");
          } else if (currentCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            System.out.print(currentCell.getNumericCellValue() + "--");
          }

        }
        System.out.println();
      }
    } catch (IOException|NullPointerException|InvalidFormatException e) {
      e.printStackTrace();
    }
  }

  private static class DDReferenceVersion {
    //add reference documents here
    public static final String v1_7 = "RESO.Dictionary.Final.v.1.7.0.20190124T0000.xlsx";
  }

}
