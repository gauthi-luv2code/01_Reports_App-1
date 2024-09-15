package in.gautham.request;


import lombok.Data;

@Data
public class SearchRequest {    // purpose of this class - form binding

	private String planName;
	private String planStatus;
	private String gender;
	
	private String startDate;	// LocalDate formate - yyyy-mm-dd
	private String endDate;
}
