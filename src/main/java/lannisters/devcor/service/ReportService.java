package lannisters.devcor.service;


public interface ReportService {

	public Object getOrdersReport(String d1,String d2);
	public Object getTechniciansReport(String d1,String d2);
	public Object getTechniciansReportById(String d1,String d2,int id);
	public Object getDevicesReport(String d1,String d2);
}
