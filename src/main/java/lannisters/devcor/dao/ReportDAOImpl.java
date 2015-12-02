package lannisters.devcor.dao;

import java.util.List;
import lannisters.devcor.entity.Report;
import lannisters.devcor.orm.ReportMapperOrders;
import lannisters.devcor.orm.ReportMapperTechnicians;
import lannisters.devcor.orm.ReportMapperDevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ReportDAOImpl implements ReportDAO {

	private static final String SQL_SELECT_ORDER_REPORT = "SELECT request.request_id, problem_type.problem_type, request.description, room.room_number,"
			+ " device.device_serial_id, execution_status.execution_status, urgency_status.urgency_status, request.creation_date, "
			+ "request.due_date, author.first_name as author_name, author.last_name as author_surname, technician.first_name as technician_name, "
			+ "technician.last_name as technician_surname"
			+ " FROM (((((((request INNER JOIN problem_type ON request.problem_type_id = problem_type.problem_type_id) "
			+ "INNER JOIN room ON request.room_id = room.room_id) INNER JOIN device ON request.device_id = device.device_id) "
			+ "INNER JOIN execution_status ON request.execution_status_id = execution_status.execution_status_id)"
			+ " INNER JOIN urgency_status ON request.urgency_status_id = urgency_status.urgency_status_id) "
			+ "INNER JOIN player author ON request.author_id = author.player_id) "
			+ "INNER JOIN player technician ON request.technician_id = technician.player_id) "
			+ "where request.creation_date between TO_DATE(?,'yyyy.mm.dd hh24:mi:ss') and TO_DATE(?,'yyyy.mm.dd hh24:mi:ss')";

	private static final String SQL_SELECT_TECHNICIANS_REPORT = "select first_name as first_name,last_name as last_name,player_id,"
			+ "(select  count  (request_id) from request  where EXECUTION_STATUS_ID = 1 and technician_id = player.player_id and creation_date between TO_DATE(?,'yyyy.mm.dd hh24:mi:ss') and TO_DATE(?,'yyyy.mm.dd hh24:mi:ss')) as openn,"
			+ "(select  count  (request_id) from request  where EXECUTION_STATUS_ID = 2 and technician_id = player.player_id and creation_date between TO_DATE(?,'yyyy.mm.dd hh24:mi:ss') and TO_DATE(?,'yyyy.mm.dd hh24:mi:ss')) as Inprogress, "
			+ "(select  count  (request_id) from request  where EXECUTION_STATUS_ID = 5 and technician_id = player.player_id and creation_date between TO_DATE(?,'yyyy.mm.dd hh24:mi:ss') and TO_DATE(?,'yyyy.mm.dd hh24:mi:ss')) as Unsolvable, "
			+ "(select  count  (request_id) from request  where EXECUTION_STATUS_ID = 4 and technician_id = player.player_id and creation_date between TO_DATE(?,'yyyy.mm.dd hh24:mi:ss') and TO_DATE(?,'yyyy.mm.dd hh24:mi:ss')) as Incorrect, "
			+ "(select  count  (request_id) from request  where EXECUTION_STATUS_ID = 3 and technician_id = player.player_id and creation_date between TO_DATE(?,'yyyy.mm.dd hh24:mi:ss') and TO_DATE(?,'yyyy.mm.dd hh24:mi:ss')) as Finished, "
			+ "(select  count  (request_id) from request  where EXECUTION_STATUS_ID = 3 and  technician_id = player.player_id and due_date < current_timestamp and creation_date between TO_DATE(?,'yyyy.mm.dd hh24:mi:ss') and TO_DATE(?,'yyyy.mm.dd hh24:mi:ss')) as FinishedwithOverdue, "
			+ "(select  count  (request_id) from request  where EXECUTION_STATUS_ID <> 3 and  technician_id = player.player_id and due_date < current_timestamp  and creation_date between TO_DATE(?,'yyyy.mm.dd hh24:mi:ss') and TO_DATE(?,'yyyy.mm.dd hh24:mi:ss')) as notFinishedwithOverdue, "
			+ "(select  count  (request_id) from request  where technician_id = player.player_id and creation_date between TO_DATE(?,'yyyy.mm.dd hh24:mi:ss') and TO_DATE(?,'yyyy.mm.dd hh24:mi:ss')) as total "
			+ "from player " + "where player.role_id=2 order by last_name";
	
	private static final String SQL_SELECT_TECHNICIANS_REPORT_BY_ID = "select first_name as first_name,last_name as last_name,player_id,"
			+ "(select  count  (request_id) from request  where EXECUTION_STATUS_ID = 1 and technician_id = player.player_id and creation_date between TO_DATE(?,'yyyy.mm.dd hh24:mi:ss') and TO_DATE(?,'yyyy.mm.dd hh24:mi:ss')) as openn,"
			+ "(select  count  (request_id) from request  where EXECUTION_STATUS_ID = 2 and technician_id = player.player_id and creation_date between TO_DATE(?,'yyyy.mm.dd hh24:mi:ss') and TO_DATE(?,'yyyy.mm.dd hh24:mi:ss')) as Inprogress, "
			+ "(select  count  (request_id) from request  where EXECUTION_STATUS_ID = 5 and technician_id = player.player_id and creation_date between TO_DATE(?,'yyyy.mm.dd hh24:mi:ss') and TO_DATE(?,'yyyy.mm.dd hh24:mi:ss')) as Unsolvable, "
			+ "(select  count  (request_id) from request  where EXECUTION_STATUS_ID = 4 and technician_id = player.player_id and creation_date between TO_DATE(?,'yyyy.mm.dd hh24:mi:ss') and TO_DATE(?,'yyyy.mm.dd hh24:mi:ss')) as Incorrect, "
			+ "(select  count  (request_id) from request  where EXECUTION_STATUS_ID = 3 and technician_id = player.player_id and creation_date between TO_DATE(?,'yyyy.mm.dd hh24:mi:ss') and TO_DATE(?,'yyyy.mm.dd hh24:mi:ss')) as Finished, "
			+ "(select  count  (request_id) from request  where EXECUTION_STATUS_ID = 3 and  technician_id = player.player_id and due_date < current_timestamp and creation_date between TO_DATE(?,'yyyy.mm.dd hh24:mi:ss') and TO_DATE(?,'yyyy.mm.dd hh24:mi:ss')) as FinishedwithOverdue, "
			+ "(select  count  (request_id) from request  where EXECUTION_STATUS_ID <> 3 and  technician_id = player.player_id and due_date < current_timestamp  and creation_date between TO_DATE(?,'yyyy.mm.dd hh24:mi:ss') and TO_DATE(?,'yyyy.mm.dd hh24:mi:ss')) as notFinishedwithOverdue, "
			+ "(select  count  (request_id) from request  where technician_id = player.player_id and creation_date between TO_DATE(?,'yyyy.mm.dd hh24:mi:ss') and TO_DATE(?,'yyyy.mm.dd hh24:mi:ss')) as total "
			+ "from player " + "where player.role_id=2 AND player.player_id = ?";
	
	private static final String SQL_SELECT_DEVICE_REPORT = "SELECT DISTINCT device.device_serial_id as serId, device_type.device_type as devType, room.room_number as roomNum,"
			+ " (select count( request.request_id) from request  where request.device_id = device.device_id ) as quantity "
			+ "FROM (((device INNER JOIN device_type ON device_type.device_type_id = device.device_type_id)"
			+ "INNER JOIN room ON device.room_id=room.room_id)INNER JOIN request ON device.device_id = request.device_id)"
			+ " where request.problem_type_id = 1 and request.creation_date between TO_DATE(?,'yyyy.mm.dd hh24:mi:ss') and TO_DATE(?,'yyyy.mm.dd hh24:mi:ss') "
			+ "order by device.device_serial_id ";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Report> getOrdersReport(String dayFrom, String dayTo) {
		return jdbcTemplate.query(SQL_SELECT_ORDER_REPORT, new ReportMapperOrders(), dayFrom, dayTo);
	}

	@Override
	public List<Report> getTechniciansReport(String dayFrom, String dayTo) {
		return jdbcTemplate.query(SQL_SELECT_TECHNICIANS_REPORT, new ReportMapperTechnicians(), dayFrom, dayTo, dayFrom,
				dayTo, dayFrom, dayTo, dayFrom, dayTo, dayFrom, dayTo, dayFrom, dayTo, dayFrom, dayTo, dayFrom, dayTo);
	}
	
	@Override
	public Report getTechniciansReportById(String dayFrom, String dayTo,int id) {
		return jdbcTemplate.queryForObject(SQL_SELECT_TECHNICIANS_REPORT_BY_ID, new ReportMapperTechnicians(), dayFrom, dayTo, dayFrom,
				dayTo, dayFrom, dayTo, dayFrom, dayTo, dayFrom, dayTo, dayFrom, dayTo, dayFrom, dayTo, dayFrom, dayTo, id);
	}

	@Override
	public List<Report> getDevicesReport(String dayFrom, String dayTo) {
		return jdbcTemplate.query(SQL_SELECT_DEVICE_REPORT, new ReportMapperDevices(), dayFrom, dayTo);
	}
}