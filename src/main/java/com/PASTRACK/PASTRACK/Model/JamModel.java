package com.PASTRACK.PASTRACK.Model;
import java.io.Serializable;
import java.sql.Time;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.format.annotation.DateTimeFormat;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "jam")
public class JamModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, name = "mulai")
    @DateTimeFormat(pattern = "HH:mm")
    private Time waktuMulai;

    @NotNull
    @Column(nullable = false, name = "akhir")
    @DateTimeFormat(pattern = "HH:mm")
    private Time waktuAkhir;

    @OneToMany(mappedBy = "jam", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List <WaktuKegiatan> waktuKegiatan;
}