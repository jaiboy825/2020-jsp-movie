package movie;

import java.sql.Timestamp;

public class BookVO {
	private String movieName;
	private String category;
	private String movieImg;
	private int runtime;
	private Timestamp runDay;
	private int roomNo;
	private int seatNo;
	private String id;
	private int ticketNo;

	

	public BookVO(String movieName, String category, String movieImg, int runtime,Timestamp runDay, int roomNo,
			int seatNo, String id, int ticketNo) {
		super();
		this.movieName = movieName;
		this.category = category;
		this.movieImg = movieImg;
		this.runtime = runtime;
		this.runDay = runDay;
		this.roomNo = roomNo;
		this.seatNo = seatNo;
		this.id = id;
		this.ticketNo = ticketNo;
	}

	public BookVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getticketNo() {
		return ticketNo;
	}

	public void setticketNo(int ticketNo) {
		this.ticketNo = ticketNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getMovieImg() {
		return movieImg;
	}

	public void setMovieImg(String movieImg) {
		this.movieImg = movieImg;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	public Timestamp getRunDay() {
		return runDay;
	}

	public void setRunDay(Timestamp runDay) {
		this.runDay = runDay;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public int getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}

	@Override
	public String toString() {
		return "BookVO [movieName=" + movieName + ", category=" + category + ", movieImg=" + movieImg + ", runtime="
				+ runtime + ", runDay=" + runDay + ", roomNo=" + roomNo + ", seatNo=" + seatNo + ", id=" + id
				+ ", ticketNo=" + ticketNo + ", getticketNo()=" + getticketNo() + ", getId()=" + getId() + ", getMovieName()="
				+ getMovieName() + ", getCategory()=" + getCategory() + ", getMovieImg()=" + getMovieImg()
				+ ", getRuntime()=" + getRuntime() + ", getRunDay()=" + getRunDay() + ", getRoomNo()=" + getRoomNo()
				+ ", getSeatNo()=" + getSeatNo() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	

}
