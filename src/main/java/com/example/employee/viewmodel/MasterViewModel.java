package com.example.employee.viewmodel;

public class MasterViewModel {
	public int totalRows;
	public BaseApiResponse baseResponse;	
	
	public int getTotalRows() {
		return totalRows;
	}
	public BaseApiResponse getBaseResponse() {
		return baseResponse;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	public void setBaseResponse(BaseApiResponse baseResponse) {
		this.baseResponse = baseResponse;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MasterViewModel [totalRows=");
		builder.append(totalRows);
		builder.append(", baseResponse=");
		builder.append(baseResponse);
		builder.append("]");
		return builder.toString();
	}	
	
	
	
}
