package com.PASTRACK.PASTRACK.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(value={"resep"}, allowSetters = true)
@Table(name = "student_komponen")

public class StudentKomponenModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "nilai_komponen")
    private Integer nilai_komponen;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    StudentModel student;

    @ManyToOne
    @JoinColumn(name = "komponen_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    KomponenModel komponen;

    
}
