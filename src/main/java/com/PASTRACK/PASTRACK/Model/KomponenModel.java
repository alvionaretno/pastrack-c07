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

@Table(name= "komponen")

public class KomponenModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kode;

    @NotNull
    @Size(max = 50)
    @Column(name = "title", nullable = false)
    private String title;


    @NotNull
    @Size(max = 300)
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Column(name = "is_released", nullable = false)
    private Boolean isReleased;

    @NotNull
    @Column(nullable = false, name = "dueDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dueDate;

    @NotNull
    @Column(nullable = false, name = "bobot")
    private int bobot;


    //baru
    @Column(nullable = false, name = "nilai_komponen")
    private Integer nilaiComponent;

    // @NotNull
    // @Column(nullable = false, name = "akhir_tahun_ajaran")
    // @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    // private LocalDateTime akhirTahunAjaran;

    // @NotNull
    // @Column(nullable = false, name = "awal_tahun_ajaran")
    // @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    // private LocalDateTime awalTahunAjaran;

    @JsonIgnore
    @ManyToOne(fetch= FetchType.EAGER, optional = false)
    @JoinColumn(name = "matapelajaranId",referencedColumnName= "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private MataPelajaranModel matapelajaran;
    
    @JsonIgnore
    @OneToMany(mappedBy = "komponen", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List <StudentKomponenModel> listNilai;

    @JsonIgnore
    @ManyToOne(fetch= FetchType.EAGER, optional = false)
    @JoinColumn(name = "semesterId",referencedColumnName= "id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private SemesterModel semester;
}