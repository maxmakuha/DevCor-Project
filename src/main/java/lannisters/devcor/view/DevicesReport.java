package lannisters.devcor.view;

import java.awt.Color;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.web.servlet.view.document.AbstractExcelView;
import lannisters.devcor.entity.Report;

public class DevicesReport extends AbstractExcelView{
	
	@Override
	protected void buildExcelDocument(Map model, HSSFWorkbook workbook,	HttpServletRequest request, HttpServletResponse response)throws Exception {
		List<Report> getAllReport = (List<Report>) model.get("reportData");
		HSSFSheet sheet = workbook.createSheet("DeviceReport");
		sheet.setDefaultColumnWidth(20);
		HSSFRow header = sheet.createRow(0);
		
		header.createCell(0).setCellValue("Serial number");
		header.createCell(1).setCellValue("Device type");
		header.createCell(2).setCellValue("Room number");
		header.createCell(3).setCellValue("Orders quantity");
		
		for (int i = 0; i < 4; i++) {
			CellStyle stylerowHeading = workbook.createCellStyle();
			Font font = workbook.createFont();
			font.setBold(true);
			font.setColor( HSSFColor.BLUE_GREY.index);
			stylerowHeading.setFont(font);
			header.getCell(i).setCellStyle(stylerowHeading);
			
		}
		
		 for (int i = 0,j = 1; i < getAllReport.size(); i++,j++) {
			HSSFRow row = sheet.createRow(j);
			Report report = getAllReport.get(i);
			row.createCell(0).setCellValue(report.getSerialNumber());
			row.createCell(1).setCellValue(report.getDeviseType());
			row.createCell(2).setCellValue(report.getRoomNumber());
			row.createCell(3).setCellValue(report.getOrderQuantity());
		 }
	}
}
