package com.PASTRACK.PASTRACK.KomponenRequest;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class listKomponenSiswaResponse implements Serializable{
    List<getComponent> listKomponenSiswa;
    int nilaiAkhir;
}
