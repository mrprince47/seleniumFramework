package utilities;

import org.testng.annotations.DataProvider;

public class ExcelDataProvider {

    @DataProvider(name = "logindata")
    public Object[][] getData() throws Exception {

        String projectPath = System.getProperty("user.dir");
        String filePath = projectPath + "\\testdata\\logindata.xlsx";

        ExcelUtility excel = new ExcelUtility(filePath, "Sheet1");
        int rows = excel.getRowCount();
        int cols = excel.getColCount();

        Object[][] tempData = new Object[rows - 1][cols];
        int actualRowCount = 0;

        for (int i = 1; i < rows; i++) {

            if (isRowBlank(excel, i, cols)) {
                continue; // SKIP blank rows
            }

            for (int j = 0; j < cols; j++) {
                tempData[actualRowCount][j] = excel.getCellData(i, j);
            }
            actualRowCount++;
        }

        excel.close();

        Object[][] finalData = new Object[actualRowCount][cols];
        System.arraycopy(tempData, 0, finalData, 0, actualRowCount);

        return finalData;
    }

    private boolean isRowBlank(ExcelUtility excel, int rowIndex, int colCount) {
        for (int i = 0; i < colCount; i++) {
            String cell = excel.getCellData(rowIndex, i);
            if (cell != null && !cell.trim().isEmpty()) {
                return false; // not blank
            }
        }
        return true; // whole row empty
    }
}
