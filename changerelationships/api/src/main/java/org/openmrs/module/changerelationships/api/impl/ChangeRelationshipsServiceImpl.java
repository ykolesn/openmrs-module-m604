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
package org.openmrs.module.changerelationships.api.impl;

import java.util.List;
import java.util.Set;

import org.openmrs.Person;
import org.openmrs.PersonName;
import org.openmrs.Relationship;
import org.openmrs.RelationshipType;
import org.openmrs.api.PersonService;
import org.openmrs.api.context.Context;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.changerelationships.api.ChangeRelationshipsService;
import org.openmrs.module.changerelationships.api.db.ChangeRelationshipsDAO;

/**
 * It is a default implementation of {@link ChangeRelationshipsService}.
 */
public class ChangeRelationshipsServiceImpl extends BaseOpenmrsService implements ChangeRelationshipsService {
	
	protected final Log log = LogFactory.getLog(this.getClass());
	
	private ChangeRelationshipsDAO dao;
	
	/**
     * @param dao the dao to set
     */
    public void setDao(ChangeRelationshipsDAO dao) {
	    this.dao = dao;
    }
    
    /**
     * @return the dao
     */
    public ChangeRelationshipsDAO getDao() {
	    return dao;
    }
    
    @Override
    public int searchNumberOfPatients(String name, String relationshipType){
    	System.out.println("IN searchNumberOfPatients FUNCTION");
    	System.out.println("name="+name);
    	System.out.println("relationshipType="+relationshipType);
    	int numberOfPatients = 0;
    	PersonService service = Context.getPersonService();
    	List<Relationship> relationshipList = service.getAllRelationships(false);
    	for(Relationship relationship : relationshipList){
    		String personAName = relationship.getPersonA().getGivenName();
    		
    		if(name.equals(personAName) && relationship.getRelationshipType().toString().equalsIgnoreCase(relationshipType)){
    			numberOfPatients++;
    		}
    	}
    	return numberOfPatients;
    }

  //Update the patient relationship from nameIn to nameOut and the relationshipType
  	@Override
  	public void changePatientRelationships(String nameIn,String relationshipType, String nameOut) {
  		System.out.println("IN changePatientRelationship FUNCTION");
      	System.out.println("name="+nameIn);
      	System.out.println("relationshipType="+relationshipType);
      	System.out.println("nameOut="+nameOut);
      	
      	PersonService service = Context.getPersonService();
      	List<Relationship> relationshipList = service.getAllRelationships(false);
      	
      	//Need to have Iterator to Iteratate through relationships list
      	//Iterator itr = rel.iterator();
      	
      	//Loop through each iteration
      	for(Relationship relationship : relationshipList)
      	{
      		//Relationship relTmp = itr.next();
      		
      		//Get personA for the relation (personA should be the physician)
      		Person personA = relationship.getPersonA();
      		
      		//If the personA's givenName matches with nameIn
      		//getGivenName() returns String
      		if(personA.getGivenName().equals(nameIn))
      		{
      			//Then check if relationship matches relationshipType for this person
      			//getRelationshipType() returns 
      			RelationshipType type = relationship.getRelationshipType();
      			if(type.getaIsToB().equals(relationshipType) == true)
      			{
      				//Use helper getPersonOut() to find person to update to
      				//Then set personA in relationship reltmp
      				Person out = getPersonOut(relationshipList, nameOut);
      				if(out.equals(null))
      				{
      					System.out.println("Could not find: " + nameOut);
      				}
      				else //FINALLY update personA
      				{
      					relationship.setPersonA(out);
      				}
    			}
    		}
    	}
	}
	
	/*Helper function to search for personB.
	 * Takes argument iterator that contains relationship elements to iterate through
	 * Returns Person object
	*/
	private Person getPersonOut(List<Relationship> relationshipList, String nameOut)
	{
		Person out;
		
		//Loop through relationship list
		for(Relationship relationship : relationshipList)
		{
			
			
			//Search for person out
			out = relationship.getPersonA();
			
			if(out.getGivenName().equals(nameOut))
			{
				return out;
			}
		}
		
		//Default return null
		return null;
	}
	
	private boolean isNameSimilar(String name, Set<PersonName> nameSet){
		String[] splitString = name.split(" ");
		for(String nameInSplit : splitString){
			//check if first/middle/last name can be found in the PersonName set
			boolean setContainedName = false;
			for(PersonName pName : nameSet){
				if(pName.toString().equalsIgnoreCase(nameInSplit)){
					setContainedName = true;
				}
			}
			
			//if the set doesn't contain the name, assume non similar name and return
			if(!setContainedName){
				return false;
			}
			
			//if set does contain name, keep stepping through the name string
		}
		return true;
	}

}