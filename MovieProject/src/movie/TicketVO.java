package movie;

import java.sql.Date;

public class TicketVO {
	private int ticketNo;
	private Date bookDate;
	private int schNo;
	private int seatNo;
	private String id;

	public TicketVO() {
		super();
	}

	public TicketVO(int ticketNo, Date bookDate, int schNo, int seatNo, String id) {
		super();
		this.ticketNo = ticketNo;
		this.bookDate = bookDate;
		this.schNo = schNo;
		this.seatNo = seatNo;
		this.id = id;
	}

	public int getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(int ticketNo) {
		this.ticketNo = ticketNo;
	}

	public Date getBookDate() {
		return bookDate;
	}

	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}

	public int getSchNo() {
		return schNo;
	}

	public void setSchNo(int schNo) {
		this.schNo = schNo;
	}

	public int getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
