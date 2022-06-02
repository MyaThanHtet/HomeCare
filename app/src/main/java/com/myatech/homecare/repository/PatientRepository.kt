package com.myatech.homecare.repository

import com.myatech.homecare.dao.PatientDao
import com.myatech.homecare.model.Patient

class PatientRepository(private val patientDao: PatientDao) {

    suspend fun insert(patient: Patient) {
        patientDao.insertPatient(patient)
    }
    /*val getPatient: LiveData<Patient> = patientDao.getPatientById()
*/
}