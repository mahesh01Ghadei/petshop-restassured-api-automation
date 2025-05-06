package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="userdata")
	public String [][] getData() throws IOException{
		String path = "./testdata/UserData.xlsx";
		
		ExcelUtility xl = new ExcelUtility(path);
		
		int totalRows = xl.getRowCount("Sheet1");
		int totalColumns = xl.getCellCount("Sheet1", 1);
		
		String[][] data = new String[totalRows][totalColumns];
		
		for(int i=1;i<=totalRows;i++) {
			for (int j=0;j<totalColumns;j++) {
				data[i-1][j] = xl.getCellData("Sheet1", i, j);
			}
			
		}
		
		return data;
		
	}
	
	@DataProvider(name="Usernames")
	public String [] getUserNameData() throws IOException{
		String path = "./testdata/UserData.xlsx";
		
		ExcelUtility xl = new ExcelUtility(path);
		int totalRows = xl.getRowCount("Sheet1");
		String[] data = new String[totalRows];
		
		for(int i=1;i<=totalRows;i++) {
			data[i-1] = xl.getCellData("Sheet1", i, 0);
		}
		
		return data;
		
	}
}
