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
import org.openmrs.module.changerelationships.PatientRelationshipChange;
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
	
	private int searchNumber = 0;
	private ChangeRelationshipsService service = Context.getService(ChangeRelationshipsService.class);
	
	@ModelAttribute("patientSearch")
	public PatientSearch getPatientSearchObject(){
		return new PatientSearch();
	}
	
	@ModelAttribute("patientRelationshipChange")
	public PatientRelationshipChange getPatientRelationshipChangeObject(){
		return new PatientRelationshipChange();
	}
	
	@RequestMapping(value = "/module/changerelationships/manage", method = RequestMethod.GET)
	public void manage(ModelMap model) {
		model.addAttribute("user", Context.getAuthenticatedUser());
		
		//System.out.println("DOING SYSTEM OUT!!!");
		
		model.addAttribute("numberOfPatients", searchNumber);
		
		//System.out.println("numberOfPatients = "+model.get("numberOfPatients"));
		
	}
	
	//handle the patient search form
	 @RequestMapping(value="/module/changerelationships/patientSearch", method = RequestMethod.GET)
	 public String handlePatientSearchRequest(ModelMap model) {
		 PatientSearch patientSearch = new PatientSearch();
		 model.addAttribute("patientSearch", patientSearch); 
		return "patientSearch";
	 }
	
	@RequestMapping(value="/module/changerelationships/patientSearch", method = RequestMethod.POST)
	public String checkPatients(@ModelAttribute("patientSearch") PatientSearch patientSearch){	
		
		searchNumber = service.searchNumberOfPatients(patientSearch.getName(), patientSearch.getRelationshipType());

		return "redirect:/module/changerelationships/manage.form";
	}
	
	//handle the patient relationship change form
	@RequestMapping(value="/module/changerelationships/patientRelationshipChange", method = RequestMethod.GET)
	 public String handlePatientChangeRequest(ModelMap model) {
		 PatientRelationshipChange patientRelationshipChange = new PatientRelationshipChange();
		 model.addAttribute("patientRelationshipChange", patientRelationshipChange); 
		return "patientSearch";
	 }
	
	@RequestMapping(value="/module/changerelationships/patientRelationshipChange", method = RequestMethod.POST)
	public String changePatientRelationships(@ModelAttribute("patientRelationshipChange") PatientRelationshipChange patientRelationshipChange){
		service.changePatientRelationships(patientRelationshipChange.getNameIn(), patientRelationshipChange.getRelationshipType(), patientRelationshipChange.getNameOut());

		return "redirect:/module/changerelationships/manage.form";
	}
	
	
}
