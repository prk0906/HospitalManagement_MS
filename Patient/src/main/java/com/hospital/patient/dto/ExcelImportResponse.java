package com.hospital.patient.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExcelImportResponse {
    private int totalRecords;
    private int successCount;
    private int failureCount;
    private List<String> errors;
    private String message;
}
