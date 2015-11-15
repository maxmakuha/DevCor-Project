package lannisters.devcor.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lannisters.devcor.dao.ReportDAO;
import lannisters.devcor.entity.Report;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportDAO reportDao;
	
	public List<Report> getReport1(String d1,String d2) {
		return reportDao.getReport1(d1,d2);
	}
	public List<Report> getReport2(String d1,String d2) {
		return reportDao.getReport2(d1,d2);
	}
	public List<Report> getReport3(String d1,String d2) {
		return reportDao.getReport3(d1,d2);
	}

	
}