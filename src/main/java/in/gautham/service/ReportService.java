package in.gautham.service;

import java.util.List;

import in.gautham.entity.CitizenPlan;
import in.gautham.request.SearchRequest;

public interface ReportService {

	public List<String> getPlanNames();
	
	public List<String> getPlanStatuser();
	
	public List<CitizenPlan> search(SearchRequest request);
	
	public boolean exportExcel();

	public boolean exportPdf();
	
	
}
