<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<p>Hello ${user.systemId}!</p>

<h2>Information</h2>
<form:form modelAttribute="patientSearch" method="POST" action="patientSearch.form" >
   
    
        <form:label path="name">Change Person from</form:label>
        <form:input path="name" />
        &nbsp&nbsp&nbsp
  	 	<form:label path="relationshipType">Relationship Type</form:label>
  	 	<form:select path="relationshipType">
        <option value=""></option>
        <option value="All">All</option>
    	</form:select>
    	&nbsp &nbsp <input type="submit" value="Search"/>
    
    <br/>
    <br/>	
    <br/>
    <br/>	
    
    <form:label path="name">Change Person To</form:label>
        <form:input path="name" />
        &nbsp&nbsp&nbsp
  	 	<form:label path="relationshipType">Change Relationship Type To</form:label>
  	 	<form:select path="relationshipType">
        <option value="All">All</option>
        </form:select>
		&nbsp &nbsp <input type="submit" value="Change"/>
		&nbsp &nbsp <input type="submit" value="Cancel"/>    	
    		
    <br/>
    <br/>
        
            
       
</form:form>

<p>Number of Patients: ${numberOfPatients}</p>

<%@ include file="/WEB-INF/template/footer.jsp"%>