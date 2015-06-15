package net.zhigang.dante.domain.ajax;

import java.util.List;

public class DataTableData {
	private int draw;
	private long recordsTotal;
	private long recordsFiltered;
	private List<?> data;
	
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	
	public long getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public long getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	
	
}
