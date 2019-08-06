package dto;

public class PaginationQueryResultDto<T> extends QueryResultDto<T> {

	
	private int pageSize;
	private int pageNo;
	private int totalPages=-1;
	private String key;
	
	public int getPageSize() {
		return pageSize;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getTotalPages() {
		int ps=this.totalPages;
		if(ps==-1){
			ps=this.getTotalRows()/this.pageSize;
			if(this.getTotalRows()%this.pageSize!=0){
				ps++;
			}
			this.totalPages=ps;
		}
		return totalPages;
	}
	public void setPageSize(int ps) {
		this.pageSize = ps;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	@Override
	public String toString() {
		return "PaginationQueryResultDto [pageSize=" + pageSize + ", pageNo="
				+ pageNo + ", totalPages=" + totalPages + ", key=" + key + "]";
	}
}
