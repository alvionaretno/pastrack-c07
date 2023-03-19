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
@Table(name = "jadwal")

public class JadwalModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "waktu_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    WaktuKegiatan waktu;

    @ManyToOne
    @JoinColumn(name = "pelajaran_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    MataPelajaranModel pelajaran;

    
}