package com.equialencia.equivalenciaBE;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexControlador {
	
	@RequestMapping("/")
	public String getIndexPage() {
		return "index";
	}

}
