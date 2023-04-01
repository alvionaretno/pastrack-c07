package com.PASTRACK.PASTRACK.KelasRequest;

import com.PASTRACK.PASTRACK.Model.KelasModel;

import java.util.List;

public class getKelasSiswaRequest {
    List<KelasModel> listKelas;


    public getKelasSiswaRequest(List<KelasModel> listKelas){
        this.listKelas = listKelas;
    }

    public List<KelasModel> getListKelas(){return this.listKelas;}
}
