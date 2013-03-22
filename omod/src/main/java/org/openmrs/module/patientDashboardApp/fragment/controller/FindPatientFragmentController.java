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
package org.openmrs.module.patientDashboardApp.fragment.controller;

import org.apache.commons.lang.StringUtils;
import org.openmrs.*;
import org.openmrs.api.context.Context;
import org.openmrs.module.patientDashboardApp.PatientDashboardService;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.UiUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * AJAX ssearch methods for patients
 */
public class FindPatientFragmentController {

    public List<SimpleObject> search(@RequestParam(value = "q", required = false) String query,
                                     @RequestParam(value = "term", required = false) String term,
                                     @RequestParam(value = "checkedInAt", required = false) Location checkedInAt,
                                     @RequestParam(value = "maxResults", required = false) Integer maxResults,
//                                     @SpringBean PatientDashboardServiceImpl service,
                                     UiUtils ui) {

        PatientDashboardService service = Context.getService(PatientDashboardService.class);

        if (StringUtils.isBlank(query)) {
            query = term;
        }
        int resultLimit = 100;
        if(maxResults!=null && maxResults.intValue()>0){
            resultLimit = maxResults.intValue();
        }
        List<Patient> results = service.findPatients(query, checkedInAt, 0, resultLimit);
        return simplify(ui, results);
    }

//    public SimpleObject searchById(@RequestParam(value = "primaryId", required = false) String primaryId,
//                                     @SpringBean PatientService service,
//                                     UiUtils ui) {
//
//        Patient patient = service.findPatientByPrimaryId(primaryId);
//        return simplify(ui, emrProperties, patient);
//
//    }

    List<SimpleObject> simplify(UiUtils ui, List<Patient> results) {
        List<SimpleObject> patients = new ArrayList<SimpleObject>(results.size());
        for (Patient patient : results) {
            patients.add(simplify(ui, patient));
        }
        return patients;
    }

    SimpleObject simplify(UiUtils ui, Patient patient) {
        PersonName name = patient.getPersonName();
        SimpleObject preferredName = SimpleObject.fromObject(name, ui, "givenName", "middleName", "familyName", "familyName2");
        preferredName.put("fullName", name.getFullName());

//        PatientIdentifierType primaryIdentifierType = emrProperties.getPrimaryIdentifierType();
//        List<PatientIdentifier> primaryIdentifiers = patient.getPatientIdentifiers(primaryIdentifierType);

        SimpleObject o = SimpleObject.fromObject(patient, ui, "patientId", "gender", "age", "birthdate", "birthdateEstimated");
        o.put("preferredName", preferredName);
//        o.put("primaryIdentifiers", SimpleObject.fromCollection(primaryIdentifiers, ui, "identifier"));

        return o;
    }

}
