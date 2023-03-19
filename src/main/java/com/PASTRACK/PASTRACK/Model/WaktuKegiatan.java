package com.PASTRACK.PASTRACK.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

import javax.persistence.*;




@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "waktu_kegiatan")

public class WaktuKegiatan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hari_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    HariModel hari;

    @ManyToOne
    @JoinColumn(name = "jam_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    JamModel jam;

    @OneToMany(mappedBy = "waktu", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List <JadwalModel> jadwal;
    
}
