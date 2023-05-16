package com.PASTRACK.PASTRACK.DashboardGuruRequest;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.PASTRACK.PASTRACK.Model.SemesterModel;

@Data
public class DashboardGuruRequest implements Serializable {
    Long angkatanId;
    String usernameGuru;
    int page;
    int size;
}