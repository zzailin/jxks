package dto;

import java.util.List;

public class QueryResultDto<T> {
	protected  int totalRows;
	protected  List<T> rows;
	public int getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public boolean isNotFound(){
		return this.rows==null||this.totalRows<=0;
	}
	
	
}
