package com.PASTRACK.PASTRACK.DashboardGuruRequest;

import com.PASTRACK.PASTRACK.Model.StudentModel;

public class StudentAverageScoreResponse implements Comparable<StudentAverageScoreResponse> {

    private StudentModel student;
    private double averageScore;

    public StudentAverageScoreResponse(StudentModel student, double averageScore) {
        this.student = student;
        this.averageScore = averageScore;
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

    @Override
    public int compareTo(StudentAverageScoreResponse o) {
        return Double.compare(averageScore, o.averageScore);
    }
}

