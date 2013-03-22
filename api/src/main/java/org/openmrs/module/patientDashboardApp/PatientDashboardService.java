package org.openmrs.module.patientDashboardApp;

import org.openmrs.Location;
import org.openmrs.Patient;
import org.openmrs.api.OpenmrsService;

import java.util.List;

public interface PatientDashboardService extends OpenmrsService {

    public List<Patient> findPatients(String query, Location checkedInAt, Integer start, Integer maxResults);

}