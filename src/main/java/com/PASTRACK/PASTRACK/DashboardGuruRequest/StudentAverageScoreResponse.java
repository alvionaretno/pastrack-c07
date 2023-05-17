package com.PASTRACK.PASTRACK.DashboardGuruRequest;

import com.PASTRACK.PASTRACK.Model.StudentModel;

public class StudentAverageScoreResponse implements Comparable<StudentAverageScoreResponse> {

    private StudentModel student;
    private double averageScore;

    private int ranking;


    public StudentAverageScoreResponse(StudentModel student, double averageScore) {
        this.student = student;
        this.averageScore = averageScore;
        this.ranking=ranking;
    }

    public StudentModel getStudent() {
        return student;
    }

    public void setStudent(StudentModel student) {
        this.student = student;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    @Override
    public int compareTo(StudentAverageScoreResponse o) {
        return Double.compare(averageScore, o.averageScore);
    }
}

