package com.chillhub.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chillhub.app.entities.Department;
import com.chillhub.app.entities.Doctor;
import com.chillhub.app.entities.Queuer;

@Repository
public interface QueuerDao extends JpaRepository<Queuer, Integer> {
	
	List<Queuer> findByDepartment_IdOrderByAppointment_Criticality_IdAscIdAsc(int depId);
	
	List<Queuer> findByDoctor_IdOrderByAppointment_Criticality_IdAscIdAsc(int docId);
	
	List<Queuer> findByDepartmentOrderByAppointment_Criticality_IdAscIdAsc(Department department);
	
	List<Queuer> findByDoctorOrderByAppointment_Criticality_IdAscIdAsc(Doctor doctor);
	
	List<Queuer> findByDepartmentOrderByTurnAsc(Department department);

}
