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
@Table(name = "student_mata_pelajaran")

public class StudentMataPelajaranModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, name = "nilai_mata_pelajaran")
    private Integer nilai_komponen;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    StudentModel student;

    @ManyToOne
    @JoinColumn(name = "kelas_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    MataPelajaranModel matapelajaran;

}