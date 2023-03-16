package com.PASTRACK.PASTRACK.Model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
// @JsonIgnoreProperties(value={"listAppointment"}, allowSetters = true)
@Table(name="student")

public class StudentModel extends UserModel implements Serializable {

    @NotNull
    @Size(max = 50)
    @Column(name = "studentNumber", nullable = false)
    private String studentNumber;

    @JsonIgnore
    @ManyToOne(fetch= FetchType.EAGER, optional = false)
    @JoinColumn(name = "orangtuaId",referencedColumnName= "id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private OrangTuaModel orangtua;

    @JsonIgnore
    @ManyToMany(mappedBy = "listMurid")
    List<PeminatanModel> listPeminatan;
    
    @JsonIgnore
    @ManyToMany(mappedBy = "listMurid")
    List<KelasModel> listKelas;
    
    @JsonIgnore
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List <StudentKomponenModel> nilai;
    
    @JsonIgnore
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List <StudentMataPelajaranModel> nilaiAkhir;
}


