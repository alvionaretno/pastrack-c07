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
@Table(name = "nilaiAngkatan")
//this table will save average final score of student in particular batch (angkatan)
public class NilaiAngkatanModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nilaiAngkatan")
    private Integer nilaiAngkatan;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "angkatan_id", referencedColumnName = "id")
    private AngkatanModel angkatan;
}
