package lannisters.devcor.view;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.web.servlet.view.document.AbstractExcelView;
import lannisters.devcor.entity.Report;

public class TechniciansReport extends AbstractExcelView{ 
	
	@Override
	protected void buildExcelDocument(Map model, HSSFWorkbook workbook,	HttpServletRequest request, HttpServletResponse response)throws Exception {
		List<Report> getAllReport = (List<Report>) model.get("reportData");
		HSSFSheet sheet = workbook.createSheet("TechniciansReport");
		sheet.setDefaultColumnWidth(20);
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
			font.setColor( HSSFColor.BLUE_GREY.index);
			stylerowHeading.setFont(font);
			header.getCell(i).setCellStyle(stylerowHeading);
		}
		
		 for (int i = 0,j = 1; i < getAllReport.size(); i++,j++) {
			HSSFRow row = sheet.createRow(j);
			Report report = getAllReport.get(i);
			row.createCell(0).setCellValue(report.getTechnician());
			row.createCell(1).setCellValue(report.getCoutOfopenOrders());
			row.createCell(2).setCellValue(report.getCountOfinprogressOrders());
			row.createCell(3).setCellValue(report.getCountOfunsolvableOrders());
			row.createCell(4).setCellValue(report.getCountOfincorrectOrders());
			row.createCell(5).setCellValue(report.getCountOffinishedOrders());
			row.createCell(6).setCellValue(report.getCountOffinishedwithOverdueOrders());
			row.createCell(7).setCellValue(report.getCountOfnotfinishedwithOverdueOrders());
			row.createCell(8).setCellValue(report.getTotalOrders());
		 }
	}
}
