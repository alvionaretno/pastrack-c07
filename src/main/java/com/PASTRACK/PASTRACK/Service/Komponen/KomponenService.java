package com.PASTRACK.PASTRACK.Service.Komponen;

import com.PASTRACK.PASTRACK.KomponenRequest.addKomponenRequest;
import com.PASTRACK.PASTRACK.Model.KomponenModel;

public interface KomponenService {
    KomponenModel getKomponenByKode(Long kode);
    KomponenModel createKomponen(String id, addKomponenRequest komponen);
    KomponenModel updateKomponen(String idMatpel, String idKomponen, addKomponenRequest komponenModel);
    addKomponenRequest readKomponen(String kode);
}
