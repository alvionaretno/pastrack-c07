package com.PASTRACK.PASTRACK.DashboardGuruRequest;


import com.PASTRACK.PASTRACK.Model.MataPelajaranModel;
import com.PASTRACK.PASTRACK.Model.StudentModel;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class DashboardGuruResponse {

    //identifier PBI 42-43 dan 44-45
    List<MatpelAverageScore> pbi4041;

    //PBI 42-43
    List<AngkatanAverageScore> pbi4243;

    Map<String, Integer> pbi4445;

    List<StudentAverageScoreResponse> pbi5051;

    public DashboardGuruResponse (List<MatpelAverageScore> pbi4041,List<AngkatanAverageScore> pbi4243,Map<String, Integer> pbi4445,List<StudentAverageScoreResponse> pbi5051) {
        this.pbi4041 = pbi4041;
        this.pbi4243 = pbi4243;
        this.pbi4445 = pbi4445;
        this.pbi5051 = pbi5051;
    }
}