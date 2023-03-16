package com.PASTRACK.PASTRACK.Service.Komponen;

import com.PASTRACK.PASTRACK.KomponenRequest.addKomponenRequest;
import com.PASTRACK.PASTRACK.Model.KomponenModel;

public interface KomponenService {
    KomponenModel getKomponenByKode(String kode);
    KomponenModel createKomponen(String id, addKomponenRequest komponen);
}
