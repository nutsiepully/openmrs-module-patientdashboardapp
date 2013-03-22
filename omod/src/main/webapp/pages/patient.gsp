<%
	ui.decorateWith("uiCommonsLibrary", "standardEmrPage")

    def contextModel = [ patientId: patient.id, visitId: 5 ]
%>

<h1>Patient: ${ ui.format(patient) }</h1>

<h1>Global Actions</h1>
<% globalExtensions.each { extension -> %>
    ${ ui.includeFragment("uiCommonsLibrary", "extension", [ extension: extension, contextModel: contextModel ]) }
<% } %>

<h1>Active Visit Actions</h1>
<% activeVisitExtensions.each { extension -> %>
    ${ ui.includeFragment("uiCommonsLibrary", "extension", [ extension: extension, contextModel: contextModel ]) }
<% } %>
