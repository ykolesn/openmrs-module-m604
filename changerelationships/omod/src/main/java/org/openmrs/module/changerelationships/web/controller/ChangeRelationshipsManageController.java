/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.changerelationships.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.changerelationships.ChangeRelationships;
import org.openmrs.module.changerelationships.PatientSearch;
import org.openmrs.module.changerelationships.api.ChangeRelationshipsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * The main controller.
 */
@Controller
public class  ChangeRelationshipsManageController{
	
	
	
	protected final Log log = LogFactory.getLog(getClass());
	
	@ModelAttribute("patientSearch")
	public PatientSearch getPatientSearchObject(){
		return new PatientSearch();
	}
	
	@RequestMapping(value = "/module/changerelationships/manage", method = RequestMethod.GET)
	public void manage(ModelMap model) {
		model.addAttribute("user", Context.getAuthenticatedUser());
		
		//another way to get the service
		ChangeRelationshipsService service = Context.getService(ChangeRelationshipsService.class);
		System.out.println("DOING SYSTEM OUT!!!");
		int patientNumber = service.getPatientWithName("randomName");
		System.out.println("numberOfPatients = "+patientNumber);
		
		model.addAttribute("numberOfPatients", patientNumber);
		
	}
	
	 @RequestMapping(value="/module/changerelationships/patientSearch", method = RequestMethod.GET)
	 public String handleRequest(ModelMap model) {
		 System.out.println("IN GET METHOD OF PATIENTSEARCH!!!!!");
		 PatientSearch patientSearch = new PatientSearch();
		 model.addAttribute("patientSearch", patientSearch); 
		return "patientSearch";
	 }
	
	@RequestMapping(value="/module/changerelationships/patientSearch", method = RequestMethod.POST)
	public String checkPatients(@ModelAttribute("patientSearch") PatientSearch patientSearch){
		System.out.println("THIS THING HIT CHECK PATIENTS WOOOHOO!!!");
		System.out.println(patientSearch.getName());
		System.out.println(patientSearch.getRelationshipType());
		//ModelAndView mv = new ModelAndView();
		//mv.addObject("patientSearch", patientSearch);
		return "redirect:/module/changerelationships/manage.form";
	}
	
	
}
