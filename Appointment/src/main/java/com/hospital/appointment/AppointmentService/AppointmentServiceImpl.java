package com.hospital.appointment.AppointmentService;

import com.hospital.appointment.Builder.AppointmentBuilder;
import com.hospital.appointment.Clients.DoctorClient;
import com.hospital.appointment.Clients.PatientClient;
import com.hospital.appointment.Exception.AppoinmentIdNotFound;
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

import java.util.List;

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

    @Override
    public List<AppointmentResponseDTO> createBulkAppointment(List<AppointmentCreateDTO> request) {
        if(request.size() ==0){
            throw new IllegalArgumentException("Request is empty");
        }
        List<Appointment> list = request.stream()
                .map(req -> {
                    PatientResponseDTO patientResponse = _patientClient.getPatientById(req.getPatientId());
                    DoctorResponseDTO doctorResponse = _doctorClient.getDoctorById(req.getDoctorId());

                    if (patientResponse == null) {
                        throw new PatientNotFoundException("Patient with Id: " + req.getPatientId() + " is not found");
                    }
                    if (!Boolean.TRUE.equals(doctorResponse.getActive()) || Boolean.TRUE.equals(doctorResponse.getDeleted())) {
                        throw new DoctorNotFoundException("Doctor with Id: " + req.getDoctorId() + " is not found");
                    }
                    Appointment appointment = AppointmentBuilder.createAppointmentFromAppointmentCreateDto(req);
                    appointment.setStatus(AppointmentStatus.REQUESTED);
                    appointment.setActive(true);
                    appointment.setDeleted(false);

                    return appointment;
                }).toList();

        List<Appointment> appointments = _repo.saveAll(list);
        return appointments.stream().map(AppointmentBuilder::createAppointmentResponseFromAppointment).toList();
    }

    @Override
    public AppointmentResponseDTO getAppointmentById(Long id) {
        if(id == null)
            throw new IllegalArgumentException("Appointment id is empty");
        Appointment response = _repo.findByAppointmentIdAndActiveTrueAndDeletedFalse(id).orElseThrow(() -> new AppoinmentIdNotFound(String.format("Appointment Id %d is not found", id)));
        return AppointmentBuilder.createAppointmentResponseFromAppointment(response);
    }


}
