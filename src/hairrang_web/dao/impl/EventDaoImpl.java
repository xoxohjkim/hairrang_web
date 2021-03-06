package hairrang_web.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;

import hairrang_web.dao.EventDao;
import hairrang_web.ds.JndiDs;
import hairrang_web.dto.Coupon;
import hairrang_web.dto.Event;
import hairrang_web.dto.Guest;
import hairrang_web.utils.Paging;

public class EventDaoImpl implements EventDao {

	private static final EventDaoImpl instance = new EventDaoImpl();

	private EventDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	public static EventDaoImpl getInstance() {
		return instance;
	}
	
	@Override
	public ArrayList<Event> selectEventAll() {
		String sql = "SELECT * FROM EVENT ORDER BY EVENT_NO DESC";
		try (Connection con = JndiDs.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			if (rs.next()) {
				ArrayList<Event> list = new ArrayList<>();
				do {
					list.add(getEvent(rs));
				} while (rs.next());
				return list;
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return null;
	}

	@Override
	public ArrayList<Event> selectEventAllForBoard() {
		String sql = "SELECT * FROM EVENT where EVENT_STATUS = 's' ORDER BY EVENT_NO DESC";

		try (Connection con = JndiDs.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			if (rs.next()) {
				ArrayList<Event> list = new ArrayList<>();
				do {
					list.add(getEvent(rs));
				} while (rs.next());
				return list;
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return null;
	}

	private Event getEvent(ResultSet rs) throws SQLException {
		int eventNo = rs.getInt("EVENT_NO");
		String eventName = rs.getString("EVENT_NAME");
		double eventSaleRate = rs.getFloat("EVENT_SALERATE");
		LocalDate eventStart = null;
		LocalDate eventEnd = null;
		try {
			eventStart = rs.getTimestamp("EVENT_START").toLocalDateTime().toLocalDate();
		} catch(Exception e) {
		}
		try {
			eventEnd = rs.getTimestamp("EVENT_END").toLocalDateTime().toLocalDate();
		} catch(Exception e) {
		}
		String eventPic = rs.getString("EVENT_PIC");
		String eventContent = rs.getString("EVENT_CONTENT");
		String eventStatus = rs.getString("EVENT_STATUS");
		
		
		return new Event(eventNo, eventName, eventSaleRate, eventStart, eventEnd, eventPic, eventContent, eventStatus);
	}

	@Override
	public Event selectEventByNo(Event event) {
		String sql = "SELECT * FROM EVENT where event_no = ? ";

		try (Connection con = JndiDs.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setInt(1, event.getEventNo());

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return getEvent(rs);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return null;
	}


	/*@Override
	public int updateEvent(Event event) {
		String sql = "UPDATE EVENT SET EVENT_NAME = ?, EVENT_SALERATE = ?, EVENT_START = ?, "
				+ "EVENT_END = ?, EVENT_PIC = ?, EVENT_CONTENT = ? WHERE EVENT_NO = ?";
	
		try (Connection con = JndiDs.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
	
			pstmt.setString(1, event.getEventName());
			pstmt.setDouble(2, event.getEventSaleRate());
			pstmt.setDate(3, Date.valueOf(event.getEventStart()));
			pstmt.setDate(4, Date.valueOf(event.getEventEnd()));
			pstmt.setString(5, event.getEventPic());
			pstmt.setString(6, event.getEventContent());
			pstmt.setInt(7, event.getEventNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}*/

	
	@Override
	public Event selectEventDownSide(Event event) {
		String sql = "SELECT * FROM (SELECT * FROM EVENT WHERE not event_no = 1 AND EVENT_STATUS = 's' and EVENT_NO <? ORDER BY EVENT_NO DESC ) WHERE rownum = 1";
		try (Connection con = JndiDs.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, event.getEventNo());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return getEvent(rs);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	@Override
	public Event selectEventUpSide(Event event) {
		String sql = "SELECT * FROM (SELECT * FROM EVENT WHERE not event_no = 1 AND EVENT_STATUS = 's' and EVENT_NO > ? ) WHERE rownum = 1";
		try (Connection con = JndiDs.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, event.getEventNo());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return getEvent(rs);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}
	
	
///////?????????///////////////////////////////////////////////////////////////////////////////
	
	@Override
	public int countEvent(Paging paging, String status) {
		String sql = "SELECT COUNT(*) FROM event ";
		
		if(status == null || status.equals("")) {
		} else {
			sql += "where event_status = '" + status + "' "; 
		}
		
		try (Connection con = JndiDs.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return 0;
	}

	@Override
	public ArrayList<Event> PagingEventAll(Paging paging, String status) {
		String sql = "SELECT * FROM (SELECT rownum RN, a.* FROM (SELECT * FROM event ";
		
		if(status == null || status.equals("")) {
		} else {
			sql += "where event_status = '" + status + "' "; 
		}
		
		sql +=" ORDER BY event_no desc) a) WHERE rn BETWEEN ? AND ? ORDER BY rn";
		
		System.out.println("sql=" + sql);
		try (Connection con = JndiDs.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, paging.getStart());
			pstmt.setInt(2, paging.getEnd());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					ArrayList<Event> list = new ArrayList<>();
					do {
						list.add(getEvent(rs));
					} while (rs.next());

					return list;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	@Override
	public int selectMaxEventNo() {
		String sql = "SELECT max(EVENT_NO) FROM EVENT";
		  try (Connection con = JndiDs.getConnection();
	        		PreparedStatement pstmt = con.prepareStatement(sql);
	                ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) return rs.getInt(1);
	        } catch (SQLException e) {
	            throw new RuntimeException();
	        }
	        return 0;
	}



	
}
