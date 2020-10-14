package hairrang_web.service;

import java.util.ArrayList;

import hairrang_web.dao.GuestDao;
import hairrang_web.dao.impl.GuestDaoImpl;
import hairrang_web.dto.Guest;

public class GuestService{
	private GuestDao dao = GuestDaoImpl.getInstance();
	
	public ArrayList<Guest> selectGuestAll(){
		return dao.selectGuestAll();
	}
	
	public Guest selectGuestById(Guest guest) {
		return dao.selectGuestById(guest);
	}
	
	public int insertGuest(Guest guest) {
		return dao.insertGuest(guest);
	}
	
	public int updateGuestPwd(Guest guest) {
		return dao.updateGuestPwd(guest);
	}
	
	public int updateGuest(Guest guest) {
		return dao.updateGuest(guest);
	}
	
	public Guest selectGuestByIdName(Guest guest) {
		return dao.selectGuestByIdName(guest);
	}
	
	public int confirmId(String id) {
		return dao.confirmId(id);
	}
	
	public Guest findId(String name, String email) {
		return dao.findId(name, email);
	}

	public Guest findPwd(String id, String name, String email) {
		
		return dao.findPwd(id, name, email);
	}

}
