package com.PASTRACK.PASTRACK.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDateTime;
// import java.util.List;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table(name= "matapelajaran")

public class MataPelajaranModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "nama_mata_pelajaran", nullable = false)
    private String namaMataPelajaran;

    // @NotNull
    // @Column(nullable = false, name = "awal_tahun_ajaran")
    // @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    // private LocalDateTime awalTahunAjaran;

    // @NotNull
    // @Column(nullable = false, name = "akhir_tahun_ajaran")
    // @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    // private LocalDateTime akhirTahunAjaran;

    @Column(name = "deskripsi", nullable = true)
    private String deskripsi;

    @JsonIgnore
    @ManyToOne(fetch= FetchType.EAGER, optional = false)
    @JoinColumn(name = "peminatanId",referencedColumnName= "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PeminatanModel peminatan;
    
    @JsonIgnore
    @ManyToOne(fetch= FetchType.EAGER, optional = false)
    @JoinColumn(name = "guruId",referencedColumnName= "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private GuruModel guru;
    
    @JsonIgnore
    @ManyToOne(fetch= FetchType.EAGER, optional = true)
    @JoinColumn(name = "kelasId",referencedColumnName= "id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private KelasModel kelas;
    
    @JsonIgnore
    @OneToMany(mappedBy = "matapelajaran", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<KomponenModel> listKomponen;

    @OneToMany(mappedBy = "matapelajaran", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List <StudentMataPelajaranModel> nilaiAkhir;

    @OneToMany(mappedBy = "pelajaran", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List <JadwalModel> jadwal;
    
    @OneToMany(mappedBy = "pelajaran", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List <PostinganTugasModel> listpostinganTugas;

    @JsonIgnore
    @ManyToOne(fetch= FetchType.EAGER, optional = false)
    @JoinColumn(name = "semesterId",referencedColumnName= "id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private SemesterModel semester;

//
//    @OneToMany(mappedBy = "pelajaran", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List <PostinganTugasModel> listpostinganTugas;

}
