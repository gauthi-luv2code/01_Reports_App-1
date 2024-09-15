package in.gautham.service;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;


import in.gautham.entity.CitizenPlan;
import in.gautham.repo.CitizenPlanRepository;
import in.gautham.request.SearchRequest;
import in.gautham.util.EmailUtils;
import in.gautham.util.ExcelGenerator;
import in.gautham.util.PdfGenerator;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	private CitizenPlanRepository planRepo;
	
	@Autowired
	private ExcelGenerator excelGenerator;
	
	@Autowired
	private PdfGenerator pdfGenerator ;
	
	
	@Autowired
	private EmailUtils emailUtils ;
	

	@Override
	public List<String> getPlanNames() {
		return planRepo.getPlanNames();
	}

	@Override
	public List<String> getPlanStatuser() {
		return planRepo.getPlanStatus();
	}

	@Override
	public List<CitizenPlan> search(SearchRequest request) {
		
		CitizenPlan entity = new CitizenPlan();
		
		if(null!=request.getPlanName() && !"".equals(request.getPlanName())) {
			entity.setPlanName(request.getPlanName());
		}
		
		if(null!=request.getPlanStatus() && !"".equals(request.getPlanStatus())) {
			entity.setPlanStatus(request.getPlanStatus());
		}
		
		if(null!=request.getGender() && !"".equals(request.getGender())) {
			entity.setGender(request.getGender());
		}
		
		if(null!=request.getStartDate() && !"".equals(request.getStartDate())) {
			
			String startDate = request.getStartDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			  //convert String to LocalDate
			  LocalDate localDate = LocalDate.parse(startDate, formatter);
			  entity.setPlanStartDate(localDate);
		}
		
		
			if(null!=request.getEndDate() && !"".equals(request.getEndDate())) {
			
			String endDate = request.getEndDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			  //convert String to LocalDate
			  LocalDate localDate = LocalDate.parse(endDate, formatter);
			  entity.setPlanStartDate(localDate);
		}
		
		
		return planRepo.findAll(Example.of(entity));
	}

	@Override
	public boolean exportExcel(HttpServletResponse response) throws Exception {

		File f = new File("Plans.xls");
		
		List<CitizenPlan> plans = planRepo.findAll();
		excelGenerator.generate(response, plans, f);
		
		String subject = "Test mail";
		String body = "<h1>Test mail body</h1>";
		String to = "learningtime.dontkilltime@gmail.com";
		
		
		emailUtils.sendEmail(subject, body, to, f);
		
		f.delete();
		
		return true;
	}

	@Override
	public boolean exportPdf(HttpServletResponse response) throws Exception {
		
		
		File f = new File("Plans.pdf");
		List<CitizenPlan> plans = planRepo.findAll();
		pdfGenerator.generate(response, plans, f);
		
		String subject = "Test mail";
		String body = "<h1>Test mail body</h1>";
		String to = "learningtime.dontkilltime@gmail.com";
		
		
		emailUtils.sendEmail(subject, body, to, f);
		
		f.delete();
		
		return true;  
	}

	

}
