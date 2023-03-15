package com.PASTRACK.PASTRACK.MatpelRequest;

import lombok.Data;

@Data
public class addMatpelResponse {
    String namaMataPelajaran;
    Boolean semester;
    public addMatpelResponse (String namaMataPelajaran) {
        this.namaMataPelajaran = namaMataPelajaran;
        
    }
}
