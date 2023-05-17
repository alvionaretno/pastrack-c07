package com.PASTRACK.PASTRACK.SemesterRequest;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class addSemesterRequest {
    @NotNull
    private boolean semester;
    private LocalDateTime awalTahunAjaran;
    private LocalDateTime akhirTahunAjaran;

    // constructors, getters, and setters
}
