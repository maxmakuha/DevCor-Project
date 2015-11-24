package lannisters.devcor.view;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.web.servlet.view.document.AbstractExcelView;
import lannisters.devcor.entity.Report;

public class DevicesReport extends AbstractExcelView{
	
	@Override
	protected void buildExcelDocument(Map model, HSSFWorkbook workbook,	HttpServletRequest request, HttpServletResponse response)throws Exception {
		List<Report> getAllReport = (List<Report>) model.get("reportData");
		HSSFSheet sheet = workbook.createSheet("DevCorReport");
		HSSFRow header = sheet.createRow(0);
		
		header.createCell(0).setCellValue("Serial number");
		header.createCell(1).setCellValue("Device type");
		header.createCell(2).setCellValue("Room number");
		header.createCell(3).setCellValue("Orders quantity");
		for (int i = 0; i < 4; i++) {
			CellStyle stylerowHeading = workbook.createCellStyle();
			Font font = workbook.createFont();
			font.setBold(true);
			stylerowHeading.setFont(font);
			stylerowHeading.setVerticalAlignment(CellStyle.ALIGN_CENTER);
			stylerowHeading.setWrapText(true);
			header.getCell(i).setCellStyle(stylerowHeading);
		}
		int rowNum = 1;
		 for (int k = 0; k < getAllReport.size(); k++) {
			HSSFRow row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(getAllReport.get(k).getSerialNumber());
			row.createCell(1).setCellValue(getAllReport.get(k).getDeviseType());
			row.createCell(2).setCellValue(getAllReport.get(k).getRoomNumber());
			row.createCell(3).setCellValue(getAllReport.get(k).getOrderQuantity());
		 }
	}
}
