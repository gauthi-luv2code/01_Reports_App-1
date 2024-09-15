package in.gautham.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.gautham.entity.CitizenPlan;
import in.gautham.request.SearchRequest;
import in.gautham.service.ReportService;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ReportController {

	@Autowired
	private ReportService service;
	
	@GetMapping("/pdf")
	public void pdfExport(HttpServletResponse response, Model model) throws Exception {
		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "attachment;filename=plans.pdf");  // xls
		boolean status = service.exportPdf(response);
		if(status) {
			model.addAttribute("msg", "PDF Report Sent To Your Email");
		}
	}
	
	@GetMapping("/excel")
	public void excelExport(HttpServletResponse response, Model model) throws Exception {
		response.setContentType("application/octet-stream");
		response.addHeader("Content-Disposition", "attachment;filename=plans.xlsx");  // xls
		boolean status = service.exportExcel(response);
		if(status) {
			model.addAttribute("msg", "Excel Report Sent To Your Email");
		}
		
	}
	
	
	@PostMapping("/search")
	public String handleSearch(@ModelAttribute("search") SearchRequest request, Model model) {
		
		System.out.println(request);
		
		List<CitizenPlan> plans = service.search(request);
		model.addAttribute("plans",plans);
		
		init(model);
		
		return "index";
		
	}
	
	/** THIS IS DOCUMENTATION COMMENT
	 * This method is used to load index page
	 * @param model
	 * @return string
	 */
	
	@GetMapping("/")
	public String indexPage(Model model) {
		
		
//		SearchRequest searchObj = new SearchRequest();
		
		model.addAttribute("search", new SearchRequest());
		init(model);
		
		return "index";
	}

	private void init(Model model) {
		
		model.addAttribute("names", service.getPlanNames());
		model.addAttribute("status", service.getPlanStatuser());
	}
}
