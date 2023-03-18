package com.PASTRACK.PASTRACK.kelasMatpelRequest;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class addMatpelToKelasRequest implements Serializable {
    String namaMatpel;
}
