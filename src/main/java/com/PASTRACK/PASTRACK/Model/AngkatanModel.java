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
@Table(name = "angkatan")
public class AngkatanModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "angkatan", nullable = false)
    private String angkatan;

    @JsonIgnore
    @OneToMany(mappedBy = "angkatan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<StudentModel> listStudent;

    @OneToOne(mappedBy = "angkatan")
    private NilaiAngkatanModel nilaiAngkatan;


}
