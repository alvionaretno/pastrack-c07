package com.PASTRACK.PASTRACK.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDateTime;
// import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table(name= "kelas")

public class KelasModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "nama_kelas", nullable = false)
    private String namaKelas;

    @JsonIgnore
    @ManyToOne(fetch= FetchType.EAGER, optional = false)
    @JoinColumn(name = "guruId",referencedColumnName= "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private GuruModel guru;

    @JsonIgnore
    @ManyToOne(fetch= FetchType.EAGER, optional = false)
    @JoinColumn(name = "semesterId",referencedColumnName= "id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private SemesterModel semester;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "student_kelas", joinColumns = @JoinColumn(name = "kelas_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
    List<StudentModel> listMurid;

    @JsonIgnore
    @OneToMany(mappedBy = "kelas", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MataPelajaranModel> listMataPelajaran;
}
