package com.PASTRACK.PASTRACK.KomponenRequest;

import java.io.Serializable;
import java.time.LocalDate;


import lombok.Data;

@Data
public class addKomponenRequest implements Serializable {
    String namaKomponen;
    LocalDate dueDate;
    int bobot;
    String desc;
}
