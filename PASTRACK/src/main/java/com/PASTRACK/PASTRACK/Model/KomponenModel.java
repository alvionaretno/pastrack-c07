package com.PASTRACK.PASTRACK.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;



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
    private String kode;

    @NotNull
    @Size(max = 50)
    @Column(name = "title", nullable = false)
    private String title;


    @NotNull
    @Size(max = 300)
    @Column(name = "description", nullable = false)
    private String Description;

    @NotNull
    @Column(name = "is_released", nullable = false)
    private Boolean isReleased;

    @NotNull
    @Column(nullable = false, name = "dueDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dueDate;

    @NotNull
    @Column(nullable = false, name = "akhir_tahun_ajaran")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime akhirTahunAjaran;

    @ManyToOne(fetch= FetchType.EAGER, optional = false)
    @JoinColumn(name = "matapelajaranId",referencedColumnName= "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private MataPelajaranModel matapelajaran;

    @OneToMany(mappedBy = "komponen", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List <StudentKomponenModel> listNilai;
}