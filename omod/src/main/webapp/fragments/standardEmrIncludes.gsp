<%
    ui.includeJavascript("patientDashboardApp", "jquery-1.8.3.min.js", Integer.MAX_VALUE)
    ui.includeJavascript("patientDashboardApp", "jquery-ui-1.9.2.custom.min.js", Integer.MAX_VALUE - 10)
    ui.includeJavascript("patientDashboardApp", "underscore-min.js", Integer.MAX_VALUE - 10)
    ui.includeJavascript("patientDashboardApp", "knockout-2.1.0.js", Integer.MAX_VALUE - 15)
    ui.includeJavascript("patientDashboardApp", "emr.js", Integer.MAX_VALUE - 15)

    ui.includeCss("patientDashboardApp", "cupertino/jquery-ui-1.9.2.custom.min.css", Integer.MAX_VALUE - 10)

    // toastmessage plugin: https://github.com/akquinet/jquery-toastmessage-plugin/wiki
    ui.includeJavascript("patientDashboardApp", "jquery.toastmessage.js", Integer.MAX_VALUE - 20)
    ui.includeCss("uiCommonsLibrary", "jquery.toastmessage.css", Integer.MAX_VALUE - 20)

    // simplemodal plugin: http://www.ericmmartin.com/projects/simplemodal/
    ui.includeJavascript("patientDashboardApp", "jquery.simplemodal.1.4.4.min.js", Integer.MAX_VALUE - 20)

%>