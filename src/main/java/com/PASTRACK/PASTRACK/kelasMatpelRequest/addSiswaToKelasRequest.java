package com.PASTRACK.PASTRACK.kelasMatpelRequest;

import lombok.Data;

import java.io.Serializable;
@Data
public class addSiswaToKelasRequest implements Serializable {
    Long Id;
    String namaSiswa;
}