package com.PASTRACK.PASTRACK.DashboardSiswaRequest;


import lombok.Data;

@Data
public class StudentScoreDTO {

    Long semesterId;
    double score;


    public StudentScoreDTO (Long semesterId,double score) {
        this.semesterId = semesterId;
        this.score = score;
    }

}
