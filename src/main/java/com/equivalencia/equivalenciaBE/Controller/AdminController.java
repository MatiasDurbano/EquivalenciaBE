package com.equivalencia.equivalenciaBE.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.equivalencia.equivalenciaBE.Model.TablasDb.Admin;
import com.equivalencia.equivalenciaBE.Service.AdminService;

@Controller
public class AdminController {

	@Autowired
	protected AdminService adminService;
	
	public Admin findOne(Long id) {
		return this.adminService.findOne(id);
	}
	
	public Admin save(Admin admin) {
		return this.adminService.save(admin);
	}
	
}
