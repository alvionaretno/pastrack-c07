package com.PASTRACK.PASTRACK.Model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="guru")

public class GuruModel extends UserModel implements Serializable {

    @NotNull
    @Size(max = 50)
    @Column(name = "guruId", nullable = false)
    private String guruId;

    @OneToMany(mappedBy = "guru", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MataPelajaranModel> listMataPelajaran;

    @OneToMany(mappedBy = "guru", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<KelasModel> listKelas;
}
