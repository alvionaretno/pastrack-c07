package com.PASTRACK.PASTRACK.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "postingan")

public class PostinganTugasModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kodePostingan;

    @Size(max = 255)
    @NotNull
    private String judulPostingan;

    @Size(max = 255)
    private String mataPelajaran;

    @NotNull
    private Date tanggalDeadline;

    @NotNull
    private String deskripsi;

//    @ManyToOne
//    @JoinColumn(name = "pelajaran_id")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    MataPelajaranModel pelajaran;



}