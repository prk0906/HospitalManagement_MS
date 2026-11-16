package com.hospital.patient.service;

import com.hospital.patient.dto.AddressCreateDTO;
import com.hospital.patient.dto.ExcelImportResponse;
import com.hospital.patient.dto.PatientCreateDTO;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientExcelService {
    private final PatientService patientService;

    public ExcelImportResponse importPatient(MultipartFile file) throws IOException {
        List<PatientCreateDTO> patients = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())){
            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter dataFormat = new DataFormatter();
            for(int i=1;i<=sheet.getLastRowNum();i++){
                Row row = sheet.getRow(i);
                if(row == null)
                    continue;
                if (row == null) {
                    continue;
                }

                String email = dataFormat.formatCellValue(row.getCell(5)).trim();

                if (email.isEmpty()) {
                    continue;
                }
                PatientCreateDTO dto = PatientCreateDTO.builder()
                        .firstname(dataFormat.formatCellValue(row.getCell(0)).trim())
                        .lastname(dataFormat.formatCellValue(row.getCell(1)).trim())
                        .dob(parse(dataFormat.formatCellValue(row.getCell(2)).trim()))
                        .gender(dataFormat.formatCellValue(row.getCell(3)).trim())
                        .phoneNo(dataFormat.formatCellValue(row.getCell(4)).trim())
                        .email(email)
                        .addressDto(AddressCreateDTO.builder()
                                .houseNo(dataFormat.formatCellValue(row.getCell(6)).trim())
                                .street(dataFormat.formatCellValue(row.getCell(7)).trim())
                                .city(dataFormat.formatCellValue(row.getCell(8)).trim())
                                .state(dataFormat.formatCellValue(row.getCell(9)).trim())
                                .country(dataFormat.formatCellValue(row.getCell(10)).trim())
                                .zipCode(dataFormat.formatCellValue(row.getCell(11)).trim())
                                .build()
                        ).build();
                patients.add(dto);
            }
            var response = patientService.bulkInsertionForPatient(patients);
            System.out.println("Saved patients count: " + response.size());

            return ExcelImportResponse.builder()
                    .totalRecords(patients.size())
                    .successCount(patients.size())
                    .failureCount(0)
                    .errors(List.of())
                    .message("Patients imported successfully")
                    .build();
        }
    }
    private LocalDate parse(String dateValue){
        if(dateValue == null || dateValue.isBlank())
            return null;
        dateValue = dateValue.trim().replace("/", "-");
        return LocalDate.parse(dateValue);
    }
}
