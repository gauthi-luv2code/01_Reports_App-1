package in.gautham.request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SearchRequest {    // purpose of this class - form binding

	private String planName;
	private String planStatus;
	private String gender;
	private LocalDate startDate;
	private LocalDate endDate;
}
