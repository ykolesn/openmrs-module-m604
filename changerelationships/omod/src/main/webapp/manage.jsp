<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<p>Hello ${user.systemId}!</p>

<h2>Patient Information</h2>
<form:form modelAttribute="patientSearch" method="POST" action="patientSearch.form" >
   
	<table>
		<td>
        <form:label path="name"><b>Person:</b></form:label>
        <form:input path="name" />
        </td>
        <td>
  	 	<form:label path="relationshipType"><b>   Relationship Type:</b></form:label>
  	 	<form:select multiple="true" path="relationshipType">
  	 		<option value="Nurse">Doctor</option>
        	<option value="Nurse">Nurse</option>
        	<option value="Father">Father</option>
        	<option value="Mother">Mother</option>
        	<option value="All">All</option>
    	</form:select>
    	</td>
    	<td>
    	 	<input type="submit" value="Search"/>
    	</td>
    </table>
 </form:form>
 <br/>
 <p>The search criteria has ${numberOfPatients} patients</p>
 <br/><br/>	<br/><br/>	
 <h2>Change Relationship</h2>
<form:form modelAttribute="patientRelationshipChange" method="POST" action="patientRelationshipChange.form" >
	<table>
		<td>
    	<form:label path="nameIn"><b> Change Person From:</b></form:label>
    	<form:input path="nameIn" />
    	</td>
        <td>
    	<form:label path="relationshipType"><b>   Change Relationship Type To:</b></form:label>
  		<form:select multiple="true" path="relationshipType">
  			<option value="Nurse">Doctor</option>
        	<option value="Nurse">Nurse</option>
        	<option value="Father">Father</option>
        	<option value="Mother">Mother</option>
        	<option value="All">All</option>
    	</form:select>
    	</td>
    	<td>
    	<form:label path="nameOut"><b>   Change Person To:</b></form:label>
    	<form:input path="nameOut" />
    	</td>
    	<td>
		<input type="submit" value="Change"/> 
		</td>
	</table> 	          
</form:form>



<%@ include file="/WEB-INF/template/footer.jsp"%>