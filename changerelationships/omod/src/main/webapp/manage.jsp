<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<p>Hello ${user.systemId}!</p>

<h2>Information</h2>
<form:form modelAttribute="patientSearch" method="POST" action="patientSearch.form" >
   <table>
    <tr>
        <td><form:label path="name">Name</form:label></td>
        <td><form:input path="name" /></td>
        
    </tr>
    <tr>
    	<td><form:label path="relationshipType">Relationship Type</form:label></td>
        <td><form:input path="relationshipType" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  
</form:form>

<p>Number of Patients: ${numberOfPatients}</p>

<%@ include file="/WEB-INF/template/footer.jsp"%>