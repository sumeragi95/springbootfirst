package fj.king.model;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

public class Identity {
	
	@NotNull(message="Employee ID cannot be blank")
	@Min(value=1, message="Employee ID cannot be negative")
	int id_nv;
	
	@NotNull(message="Work date cannot be blank")
	@DateTimeFormat( pattern="yyyy-MM-dd")
	Date work_date;
	
	@NotEmpty(message="End time cannot be blank")
	@NotNull(message="Start date cannot be nil")
	String start_time;
	
	@NotEmpty(message="End time cannot be blank")
	@NotNull(message="End time cannot be nil")
	String end_time;
	
	public Identity() {}
	
//	public Identity(Date work_date, String start_time, String end_time) {
//		super();
//		this.work_date = work_date;
//		this.start_time = start_time;
//		this.end_time = end_time;
//	}
	public Identity(int id_nv, Date work_date, String start_time, String end_time) {
		super();
		this.id_nv = id_nv;
		this.work_date = work_date;
		this.start_time = start_time;
		this.end_time = end_time;
	}
	public int getId_nv() {
		return id_nv;
	}
	public void setId_nv(int id_nv) {
		this.id_nv = id_nv;
	}
	public Date getWork_date() {
		return work_date;
	}
	public void setWork_date(Date work_date) {
		this.work_date = work_date;
	}
	@Override
	public String toString() {
		return "Identity [id_nv=" + id_nv + ", work_date=" + work_date + ", start_time=" + start_time + ", end_time="
				+ end_time + "]";
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	
	

}
