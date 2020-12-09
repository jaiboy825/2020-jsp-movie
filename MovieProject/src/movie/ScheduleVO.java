package movie;

import java.sql.Date;
import java.sql.Timestamp;

public class ScheduleVO {
	private String movieName;
	private int schNo;
	private int movieNo;
	private Timestamp runDay;
	private int runtime;
	private int roomNo;
	private int seatCnt;

	public ScheduleVO() {
		super();
	}

	public ScheduleVO(String movieName, int schNo, int movieNo, Timestamp runDay, int runtime, int roomNo,
			int seatCnt) {
		super();
		this.movieName = movieName;
		this.schNo = schNo;
		this.movieNo = movieNo;
		this.runDay = runDay;
		this.runtime = runtime;
		this.roomNo = roomNo;
		this.seatCnt = seatCnt;
	}

	public int getSeatCnt() {
		return seatCnt;
	}

	public void setSeatCnt(int seatCnt) {
		this.seatCnt = seatCnt;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public int getSchNo() {
		return schNo;
	}

	public void setSchNo(int schNo) {
		this.schNo = schNo;
	}

	public int getMovieNo() {
		return movieNo;
	}

	public void setMovieNo(int movieNo) {
		this.movieNo = movieNo;
	}

	public Timestamp getRunDay() {
		return runDay;
	}

	public void setRunDay(Timestamp runDay) {
		this.runDay = runDay;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

}
