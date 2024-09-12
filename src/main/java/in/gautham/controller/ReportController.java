package in.gautham.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import in.gautham.service.ReportService;

@Controller
public class ReportController {

	@Autowired
	private ReportService service;
	
	@GetMapping("/")
	public String indexPage() {
		
		return "index";
	}
}
