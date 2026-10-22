package com.hospital.appointment.AppointmentService;

import com.hospital.appointment.Builder.AppointmentBuilder;
import com.hospital.appointment.Clients.DoctorClient;
import com.hospital.appointment.Clients.PatientClient;
import com.hospital.appointment.Exception.DoctorNotFoundException;
import com.hospital.appointment.Exception.PatientNotFoundException;
import com.hospital.appointment.Repository.AppointmentRepo;
import com.hospital.appointment.dtos.AppointmentCreateDTO;
import com.hospital.appointment.dtos.AppointmentResponseDTO;
import com.hospital.appointment.dtos.DoctorResponseDTO;
import com.hospital.appointment.dtos.PatientResponseDTO;
import com.hospital.appointment.entities.Appointment;
import com.hospital.appointment.entities.AppointmentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl implements IAppointmentService{

    @Autowired
    private PatientClient _patientClient;
    @Autowired
    private DoctorClient _doctorClient;
    @Autowired
    private AppointmentRepo _repo;

    @Override
    public AppointmentResponseDTO createAppointment(AppointmentCreateDTO request) {
        if(request==null){
            throw new IllegalArgumentException("Request is invalid");
        }
        PatientResponseDTO patientResponse = _patientClient.getPatientById(request.getPatientId());
        if(patientResponse == null){
            throw new PatientNotFoundException("Patient with Id: "+request.getPatientId()+" is not found");
        }
        DoctorResponseDTO doctorResponse = _doctorClient.getDoctorById(request.getDoctorId());
        if(doctorResponse == null){
            throw new DoctorNotFoundException("Doctor with Id: "+request.getDoctorId()+" is not found");
        }
        Appointment appointment = AppointmentBuilder.createAppointmentFromAppointmentCreateDto(request);
        appointment.setStatus(AppointmentStatus.REQUESTED);
        appointment.setActive(true);
        appointment.setDeleted(false);

        Appointment save = _repo.save(appointment);
        return AppointmentBuilder.createAppointmentResponseFromAppointment(save);
    }
}
