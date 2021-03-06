package hairrang_web.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Booking {
	
	private int bookNo;
	private Guest guest;
	private LocalDateTime bookDate;
	private Designer designer;
	private LocalDateTime bookRegDate;
	private int bookStatus;
	private String bookNote;
	private int rowNum;
	
	private List<BookingHairs> hairList;
	
	public Booking() {
	}

	public Booking(int bookNo) {
		this.bookNo = bookNo;
	}

	public Booking(Guest guest) {
		this.guest = guest;
	}
	
	public Booking(Guest guest, LocalDateTime bookDate, List<BookingHairs> hairList, Designer designer, String bookNote) {
		this.guest = guest;
		this.bookDate = bookDate;
		this.hairList = hairList;
		this.designer = designer;
		this.bookNote = bookNote;
	}
	
	public Booking(int bookNo, Guest guest, LocalDateTime bookDate, Designer designer, LocalDateTime bookRegDate,
			int bookStatus, String bookNote, List<BookingHairs> hairList) {
		this.bookNo = bookNo;
		this.guest = guest;
		this.bookDate = bookDate;
		this.designer = designer;
		this.bookRegDate = bookRegDate;
		this.bookStatus = bookStatus;
		this.bookNote = bookNote;
		this.hairList = hairList;
	}

	public Booking(int rowNum, int bookNo, Guest guest, LocalDateTime bookDate, Hair hair, Designer designer,
			LocalDateTime bookRegDate, int bookStatus, String bookNote) {
		this.rowNum = rowNum;
		this.bookNo = bookNo;
		this.guest = guest;
		this.bookDate = bookDate;
		this.designer = designer;
		this.bookRegDate = bookRegDate;
		this.bookStatus = bookStatus;
		this.bookNote = bookNote;
	}
	
	public Booking(int rowNum, int bookNo, Guest guest, LocalDateTime bookDate, List<BookingHairs> hairList, Designer designer,
			LocalDateTime bookRegDate, int bookStatus, String bookNote) {
		this.rowNum = rowNum;
		this.bookNo = bookNo;
		this.guest = guest;
		this.bookDate = bookDate;
		this.hairList = hairList;
		this.designer = designer;
		this.bookRegDate = bookRegDate;
		this.bookStatus = bookStatus;
		this.bookNote = bookNote;
	}
	
	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public int getBookNo() {
		return bookNo;
	}

	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public LocalDateTime getBookDate() {
		return bookDate;
	}

	public String getBookDateStr() {
		return this.bookDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
	}
	
	public String getOnlyBookDateStr() {
		return this.bookDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
	public String getBookDateStrKo() {
		return this.bookDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd(E) a h??? mm???"));
	}
	
	public void setBookDate(LocalDateTime bookDate) {
		this.bookDate = bookDate;
	}
	
	public void setBookDate(String bookDateStr) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		this.bookDate = LocalDateTime.parse(bookDateStr, formatter);
	}

	public Designer getDesigner() {
		return designer;
	}

	public void setDesigner(Designer designer) {
		this.designer = designer;
	}

	public LocalDateTime getBookRegDate() {
		return bookRegDate;
	}
	
	public String getBookRegDateStr() {
		String fmt = null;
		if(this.bookRegDate.toLocalDate().isEqual(LocalDate.now())) {
			fmt = "yyyy-MM-dd HH:mm";
		} else {
			fmt = "yyyy-MM-dd";
		}
		return this.bookRegDate.format(DateTimeFormatter.ofPattern(fmt));
//		 return this.bookRegDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
	}
	
	public String getBookRegDateStrStd() {
		return this.bookRegDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
	}

	public void setBookRegDate(LocalDateTime bookRegDate) {
		this.bookRegDate = bookRegDate;
	}

	public int getBookStatus() {
		return bookStatus;
	}

	public void setBookStatus(int bookStatus) {
		this.bookStatus = bookStatus;
	}

	public String getBookStatusStr() {
		String str = null;
		switch(bookStatus) {
			case 1: str = "?????? ??????"; break;
			case 2: str = "?????? ??????"; break;
			case 0: str = "?????? ??????"; break;
			case -1: str = "?????????"; break;
		}
		return str;
	}
	
	public String getBookNote() {
		return bookNote;
	}

	public void setBookNote(String bookNote) {
		this.bookNote = bookNote;
	}

	public List<BookingHairs> getHairList() {
		return hairList;
	}

	public void setHairList(List<BookingHairs> hairList) {
		this.hairList = hairList;
	}

	public String getHowManyHairItems() {
		if (this.hairList.size() == 1) {
			return String.format("%s", hairList.get(0).getHair().getHairName());
		}
		
		ArrayList<Hair> hairs = this.hairList.stream().map(BookingHairs::getHair)
			.sorted(Comparator.comparing(Hair::getHairPrice).reversed())
			.collect(Collectors.toCollection(ArrayList::new));
		
		return String.format("%s ??? %d ???", hairs.get(0).getHairName(), hairs.size() - 1);
	}
	
	public int getTotalPrice() {
		int totalPrice = 0;
		if(this.hairList.size() == 1) {
			totalPrice = hairList.get(0).getHair().getHairPrice() * hairList.get(0).getQuantity();
		} else {
			for(BookingHairs bh : this.hairList) {
				totalPrice += bh.getQuantity() * bh.getHair().getHairPrice();
			}
		}
		return totalPrice;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.bookNo == ((Booking) obj).bookNo;
	}

	@Override
	public String toString() {
		return String.format("Booking [bookNo=%s, guest=%s, bookDate=%s, designer=%s, hairList=%s]", bookNo, guest,
				bookDate, designer, hairList);
	}

	
	
}
