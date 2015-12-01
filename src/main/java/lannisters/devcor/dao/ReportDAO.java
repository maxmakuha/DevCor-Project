package lannisters.devcor.dao;

import java.util.List;
import lannisters.devcor.entity.Report;

public interface ReportDAO {
	public List<Report> getOrdersReport(String d1,String d2);
	public List<Report> getTechniciansReport(String d1,String d2);
	public Report getTechniciansReportById(String d1,String d2,int id);
	public List<Report> getDevicesReport(String d1,String d2);
}