package utilities;

import java.io.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class ExcelUtility {

    private String filePath;
    private FileInputStream fis;
    private FileOutputStream fos;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public ExcelUtility(String filePath, String sheetName) throws Exception {
        this.filePath = filePath;

        File file = new File(filePath);

        if (file.exists()) {
            fis = new FileInputStream(file);
            workbook = new XSSFWorkbook(fis);
        } else {
            workbook = new XSSFWorkbook();
        }

        sheet = workbook.getSheet(sheetName);

        if (sheet == null) {
            sheet = workbook.createSheet(sheetName);
        }

        if (fis != null) fis.close();
    }

    public int getRowCount() {
        return sheet.getPhysicalNumberOfRows();
    }

    public int getColCount() {
        Row row = sheet.getRow(0);
        if (row == null) return 0;
        return row.getLastCellNum();
    }

    public String getCellData(int rowIndex, int colIndex) {
        Row row = sheet.getRow(rowIndex);
        if (row == null) return "";
        Cell cell = row.getCell(colIndex);
        if (cell == null) return "";
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell);
    }

    public void setCellData(int rowIndex, int colIndex, String value) throws Exception {
        Row row = sheet.getRow(rowIndex);
        if (row == null)
            row = sheet.createRow(rowIndex);

        Cell cell = row.getCell(colIndex);
        if (cell == null)
            cell = row.createCell(colIndex);

        cell.setCellValue(value);

        fos = new FileOutputStream(filePath);
        workbook.write(fos);
        fos.close();
    }

    public void close() throws IOException {
        if (workbook != null) workbook.close();
        if (fis != null) fis.close();
        if (fos != null) fos.close();
    }
}
