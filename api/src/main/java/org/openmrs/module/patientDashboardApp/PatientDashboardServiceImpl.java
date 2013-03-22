package org.openmrs.module.patientDashboardApp;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.openmrs.Location;
import org.openmrs.Patient;
import org.openmrs.PatientIdentifierType;
import org.openmrs.Visit;
import org.openmrs.api.PatientService;
import org.openmrs.api.db.hibernate.PatientSearchCriteria;
import org.openmrs.api.impl.BaseOpenmrsService;

import java.util.ArrayList;
import java.util.List;

public class PatientDashboardServiceImpl extends BaseOpenmrsService implements PatientDashboardService{

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Patient> findPatients(String query, Location checkedInAt, Integer start, Integer maxResults) {
        Criteria criteria;
        if (checkedInAt != null) {
            criteria = sessionFactory.getCurrentSession().createCriteria(Visit.class);
            criteria.setProjection(Property.forName("patient"));
            criteria.add(Restrictions.isNull("stopDatetime"));
            criteria.add(Restrictions.eq("location", checkedInAt));
            Criteria patientCriteria = criteria.createCriteria("patient");
            if (StringUtils.isNotBlank(query)) {
                patientCriteria = buildCriteria(query, patientCriteria);
            }
            criteria = patientCriteria;
        }
        else {
            criteria = sessionFactory.getCurrentSession().createCriteria(Patient.class);
            criteria = buildCriteria(query, criteria);
        }

        if (start != null) {
            criteria.setFirstResult(start);
        }

        if (maxResults != null) {
            criteria.setMaxResults(maxResults);
        }

        return (List<Patient>) criteria.list();
    }

    private Criteria buildCriteria(String query, Criteria criteria) {
        return new PatientSearchCriteria(sessionFactory, criteria).prepareCriteria(query,
                null, new ArrayList<PatientIdentifierType>(), true, true);

//        if (query.matches(".*\\d.*")) {
//            // has at least one digit, so treat as an identifier
//            return new PatientSearchCriteria(sessionFactory, criteria).prepareCriteria(null, query, emrProperties.getIdentifierTypesToSearch(), true, true);
//        } else {
//            // no digits, so treat as a name
//        }
    }

}
