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
package org.openmrs.module.patientDashboardApp.page.controller;

import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.appframework.api.AppFrameworkService;
import org.openmrs.module.appframework.domain.Extension;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 *
 */
public class PatientPageController {

	public void get(@RequestParam("patientId") Patient patient,
	                PageModel model,
                    @SpringBean AppFrameworkService appFrameworkService) {

        List<Extension> globalExtensions = appFrameworkService.getAllExtensions("patientDashboardApp", "globalActions");
        List<Extension> activeVisitExtensions = appFrameworkService.getAllExtensions("patientDashboardApp", "activeVisitActions");

        globalExtensions.add(new Extension("Start Visit", "patientDashboardApp", "globalActions", "link", "Start Visit", "url", 0));
        globalExtensions.add(new Extension("Request Paper Record", "app1", "extensionPoint2", "link", "Request Paper Record", "provider?patientId={{patientId}}", 1));

        activeVisitExtensions.add(new Extension("Order X-Ray", "app2", "extensionPoint1", "link", "Order X-Ray",
                                    "/radiologyApp/orderXray?patientId={{patientId}}&visitId={{visitId}}", 2));

        model.addAttribute("patient", patient);
        model.addAttribute("globalExtensions", globalExtensions);
        model.addAttribute("activeVisitExtensions", activeVisitExtensions);
    }

}
