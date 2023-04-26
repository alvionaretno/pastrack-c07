package com.PASTRACK.PASTRACK.Model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "nilai_akhir_semester_siswa")
public class NilaiAkhirSiswaSemesterModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "student_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    StudentModel student;

    @ManyToOne
    @JoinColumn(name = "nilai_semester_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    NilaiSemesterModel nilaiAkhirSemester;

}