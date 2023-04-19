package com.PASTRACK.PASTRACK.Service.Kelas;

import java.util.List;

import com.PASTRACK.PASTRACK.KelasRequest.addKelasRequest;
import com.PASTRACK.PASTRACK.KelasRequest.addMatpelKelasRequest;
import com.PASTRACK.PASTRACK.KelasRequest.kelasAllRequest;
import com.PASTRACK.PASTRACK.KelasRequest.siswaAllRequest;
import com.PASTRACK.PASTRACK.Model.KelasModel;
import com.PASTRACK.PASTRACK.Model.MataPelajaranModel;
import com.PASTRACK.PASTRACK.Model.StudentModel;
import com.PASTRACK.PASTRACK.RequestAuthentication.addMuridRequest;


public interface KelasService {
    
    KelasModel createKelas(addKelasRequest kelas);

    List<KelasModel> getAllKelas();

    KelasModel addMuridToKelas (String id, addMuridRequest[] username);
    KelasModel addMatpelToKelas (String id, addMatpelKelasRequest[] listMatpel);
    KelasModel getKelasById (Long idKelas);

    MataPelajaranModel getMatpelById(Long id);
    List<kelasAllRequest> getListKelasByGuru(String usernameGuru);

    List<kelasAllRequest> getListKelasBySiswa(String usernameSiswa);

    Boolean cekIfSiswaHasBeenAssigned(List<KelasModel> listKelasInSiswa);

    List<StudentModel> getNotAssignedStudents(List<StudentModel> listSiswa);

    List<StudentModel> getNotAssignedStudents();

    List<StudentModel> getListSiswaInKelasX(String idKelas);

    List<MataPelajaranModel> getNotAssignedMatpel(List<MataPelajaranModel> listMatpel);

    List<MataPelajaranModel> getNotAssignedMatpel();
   
}