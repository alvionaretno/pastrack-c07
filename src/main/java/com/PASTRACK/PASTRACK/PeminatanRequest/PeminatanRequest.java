package com.PASTRACK.PASTRACK.PeminatanRequest;

import java.io.Serializable;

import lombok.Data;

@Data
public class PeminatanRequest implements Serializable {
    String namaPeminatan;

    public PeminatanRequest(String namaPeminatan) {
        this.namaPeminatan = namaPeminatan;
    }
}
