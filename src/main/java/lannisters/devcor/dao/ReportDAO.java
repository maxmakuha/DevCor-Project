package lannisters.devcor.dao;

import java.util.List;
import lannisters.devcor.entity.Report;

public interface ReportDAO {
	public List<Report> getReport1(String d1,String d2);
	public List<Report> getReport2(String d1,String d2);
	public List<Report> getReport3(String d1,String d2);
}