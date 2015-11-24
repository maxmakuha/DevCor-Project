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

public class TechniciansReport extends AbstractExcelView{
	
	@Override
	protected void buildExcelDocument(Map model, HSSFWorkbook workbook,	HttpServletRequest request, HttpServletResponse response)throws Exception {
		List<Report> getAllReport = (List<Report>) model.get("reportData");
		HSSFSheet sheet = workbook.createSheet("DevCorReport");
		HSSFRow header = sheet.createRow(0);
		
		header.createCell(0).setCellValue("Technician");
		header.createCell(1).setCellValue("Open");
		header.createCell(2).setCellValue("In progress");
		header.createCell(3).setCellValue("Unsolvable");
		header.createCell(4).setCellValue("Incorrect");
		header.createCell(5).setCellValue("Finished");
		header.createCell(6).setCellValue("Finished with Overdue");
		header.createCell(7).setCellValue("Not finished with Overdue");
		header.createCell(8).setCellValue("Total");
		for (int i = 0; i < 9; i++) {
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
			row.createCell(0).setCellValue(getAllReport.get(k).getTechnician());
			row.createCell(1).setCellValue(getAllReport.get(k).getCoutOfopenOrders());
			row.createCell(2).setCellValue(getAllReport.get(k).getCountOfinprogressOrders());
			row.createCell(3).setCellValue(getAllReport.get(k).getCountOfunsolvableOrders());
			row.createCell(4).setCellValue(getAllReport.get(k).getCountOfincorrectOrders());
			row.createCell(5).setCellValue(getAllReport.get(k).getCountOffinishedOrders());
			row.createCell(6).setCellValue(getAllReport.get(k).getCountOffinishedwithOverdueOrders());
			row.createCell(7).setCellValue(getAllReport.get(k).getCountOfnotfinishedwithOverdueOrders());
			row.createCell(8).setCellValue(getAllReport.get(k).getTotalOrders());
		 }
	}
}
