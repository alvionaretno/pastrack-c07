package com.PASTRACK.PASTRACK.PeminatanRequest;

import java.io.Serializable;

import lombok.Data;

@Data
public class PeminatanResponse implements Serializable {
    Long id;
    String namaPeminatan;

    public PeminatanResponse(Long id, String namaPeminatan) {
        this.id = id;
        this.namaPeminatan = namaPeminatan;
    }
}
