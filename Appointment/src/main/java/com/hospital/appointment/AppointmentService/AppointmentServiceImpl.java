package com.hospital.appointment.AppointmentService;

import com.hospital.appointment.Builder.AppointmentBuilder;
import com.hospital.appointment.Clients.DoctorClient;
import com.hospital.appointment.Clients.PatientClient;
import com.hospital.appointment.Exception.AppoinmentIdNotFound;
import com.hospital.appointment.Exception.DoctorNotFoundException;
import com.hospital.appointment.Exception.PatientNotFoundException;
import com.hospital.appointment.Repository.AppointmentRepo;
import com.hospital.appointment.dtos.*;
import com.hospital.appointment.entities.Appointment;
import com.hospital.appointment.entities.AppointmentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Override
    public List<AppointmentResponseDTO> getAppointments() {
        List<Appointment> list = _repo.findAll().stream().filter(appo->appo.getActive()==true && appo.getDeleted()==false).toList();
        if(list == null || list.size()==0)
            throw new RuntimeException("No Records Found");
        return list.stream().map(AppointmentBuilder::createAppointmentResponseFromAppointment).toList();
    }

    @Override
    public PageResponse<AppointmentResponseDTO> getAppointmentsByPagination(int pageIndex, int pageSize) {
        Pageable pageRequest = PageRequest.of(pageIndex, pageSize);
        Page<Appointment> list = _repo.findAll(pageRequest);
        List<AppointmentResponseDTO> appointmentList = list.getContent().stream()
                .filter(appo -> appo.getActive() == true && appo.getDeleted() == false)
                .map(AppointmentBuilder::createAppointmentResponseFromAppointment)
                .toList();
        return PageResponse.<AppointmentResponseDTO>builder()
                .content(appointmentList)
                .pageNo(list.getNumber())
                .pageSize(list.getSize())
                .totalElements(list.getTotalElements())
                .totalPages(list.getTotalPages())
                .last(list.isLast())
                .build();
    }

    @Override
    public AppointmentResponseDTO updateAppointment(Long id, AppointmentUpdateDTO request) {
        if(id==null)
            throw new IllegalArgumentException(("Id is null"));
        PatientResponseDTO patient = _patientClient.getPatientById(request.getPatientId());
        if(patient==null)
            throw new PatientNotFoundException("Patient with id: "+request.getPatientId()+" is not found");
        DoctorResponseDTO doctor = _doctorClient.getDoctorById(request.getDoctorId());
        if(doctor==null)
            throw new DoctorNotFoundException("Doctor with id:"+request.getDoctorId()+" is not found");
        Appointment appointment = _repo.findByAppointmentIdAndActiveTrueAndDeletedFalse(id).orElseThrow(() -> new AppoinmentIdNotFound("Appointment Id: " + id + " is not found"));
        appointment = AppointmentBuilder.updateAppointmentFromDTO(appointment, request);
        Appointment save = _repo.save(appointment);
        return AppointmentBuilder.createAppointmentResponseFromAppointment(save);
    }


}
