package com.PASTRACK.PASTRACK.SemesterRequest;

import lombok.Data;

import javax.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class addSemesterRequest {
    @NotNull
    private boolean semester;
    private LocalDate awalTahunAjaran;
    private LocalDate akhirTahunAjaran;

    // constructors, getters, and setters
}
