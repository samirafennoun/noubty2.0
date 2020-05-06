package com.chillhub.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.chillhub.app.dao.DoctorDao;
import com.chillhub.app.dao.QueuerDao;
import com.chillhub.app.entities.Doctor;
import com.chillhub.app.entities.Queuer;

@Service
public class DoctorServiceImpl implements IDoctorService {

	@Autowired
	DoctorDao dao;
	
	@Autowired
	QueuerDao queuerDao;
	
	BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
	
	@Override
	public List<Doctor> getAll() {
		return dao.findAll();
	}
	
	@Override
	public Optional<Doctor> getOneById(int id) {
		return dao.findById(id);
	}
	
	@Override
	public void addOrUpdate(Doctor d) {
		d.setPassword(d.getRefMedicale());
		d.setPassword(bcrypt.encode(d.getPassword()));
		dao.save(d);
	}
	
	@Override
	public void delete(Doctor d) {
		dao.delete(d);
	}
	
	@Override
	public Doctor getByRoom(String room) {
		return dao.findByRoom(room);
	}
	
	@Override
	public Doctor getByRefMedical(String ref) {
		return dao.findByRefMedicale(ref);
	}
	
	
	@Override
	public List<Doctor> findDispoDoc() {
		return dao.findByDisponible(true);
	}
	
	@Override
	public void changeDoctorDispo(Doctor doc) {
		if(doc.isDisponible()) {
			doc.setDisponible(false);
		} else
		{
			doc.setDisponible(true);
		}
		dao.save(doc);
	}
	
	@Override
	public List<Queuer> getQueueOrderedById(int docId) {
		return queuerDao.findByDoctor_IdOrderByAppointment_Criticality_IdAscIdAsc(docId);
	}
	
	@Override
	public List<Queuer> getQueueOrdered(Doctor doc) {
		return queuerDao.findByDoctorOrderByAppointment_Criticality_IdAscIdAsc(doc);
	}
}
