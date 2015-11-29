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

	@Override
	public List<Report> getOrdersReport(String d1, String d2) {
		return reportDao.getOrdersReport(d1, d2);
	}

	@Override
	public List<Report> getTechniciansReport(String d1, String d2) {
		return reportDao.getTechniciansReport(d1, d2);
	}

	@Override
	public List<Report> getDevicesReport(String d1, String d2) {
		return reportDao.getDevicesReport(d1, d2);
	}
}