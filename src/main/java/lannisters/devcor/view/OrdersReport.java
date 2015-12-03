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

public class OrdersReport extends AbstractExcelView{
	
	@Override
	protected void buildExcelDocument(Map model, HSSFWorkbook workbook,	HttpServletRequest request, HttpServletResponse response)throws Exception {
		List<Report> getAllReport = (List<Report>) model.get("reportData");
		HSSFSheet sheet = workbook.createSheet("DevCorReport");
		HSSFRow header = sheet.createRow(0);
		
		header.createCell(0).setCellValue("Creating date");
		header.createCell(1).setCellValue("Due date");
		header.createCell(2).setCellValue("Problem type");
		header.createCell(3).setCellValue("Problem description");
		header.createCell(4).setCellValue("Room number");
		header.createCell(5).setCellValue("Serial number");
		header.createCell(6).setCellValue("Execution status");
		header.createCell(7).setCellValue("Urgency status");
		header.createCell(8).setCellValue("Author");
		header.createCell(9).setCellValue("Overdue");
		header.createCell(10).setCellValue("Technician");
		for (int i = 0; i < 11; i++) {
			CellStyle stylerowHeading = workbook.createCellStyle();
			Font font = workbook.createFont();
			font.setBold(true);
			stylerowHeading.setFont(font);
			header.getCell(i).setCellStyle(stylerowHeading);
		}
		int rowNum = 1;
		 for (int i = 0; i < getAllReport.size(); i++) {
			HSSFRow row = sheet.createRow(rowNum++);
			Report report = getAllReport.get(i);
			row.createCell(0).setCellValue(report.getDueDate().toString());
			row.createCell(1).setCellValue(report.getDueDate().toString());
			row.createCell(2).setCellValue(report.getProblemType());
			row.createCell(3).setCellValue(report.getProblemDescription());
			row.createCell(4).setCellValue(report.getRoomNumber());
			row.createCell(5).setCellValue(report.getSerialNumber());
			row.createCell(6).setCellValue(report.getExecutionStatus());
			row.createCell(7).setCellValue(report.getUrgencyStatus());
			row.createCell(8).setCellValue(report.getAuthor());
			row.createCell(9).setCellValue(report.getOverdue());
			row.createCell(10).setCellValue(report.getTechnician());
		 }
	}
}
