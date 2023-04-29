package com.PASTRACK.PASTRACK.Service.Kelas;

import java.util.List;

import com.PASTRACK.PASTRACK.KelasRequest.*;
import com.PASTRACK.PASTRACK.Model.KelasModel;
import com.PASTRACK.PASTRACK.Model.MataPelajaranModel;
import com.PASTRACK.PASTRACK.Model.SemesterModel;
import com.PASTRACK.PASTRACK.Model.StudentMataPelajaranModel;
import com.PASTRACK.PASTRACK.Model.StudentModel;
import com.PASTRACK.PASTRACK.RequestAuthentication.addMuridRequest;


public interface KelasService {
    
    addKelasResponse createKelas(addKelasRequest kelas);

    List<KelasModel> getAllKelas();

    addKelasResponse addMuridToKelas (String id, addMuridRequest[] username);
    addKelasResponse addMatpelToKelas (String id, addMatpelKelasRequest[] listMatpel);
    addKelasResponse getKelasById (Long idKelas);

    KelasModel getById (Long idKelas);

    MataPelajaranModel getMatpelById(Long id);
    List<kelasAllRequest> getListKelasByGuru(String usernameGuru);

    List<kelasAllRequest> getListKelasBySiswa(String usernameSiswa);

    Boolean cekIfSiswaHasBeenAssigned(List<KelasModel> listKelasInSiswa);

    List<StudentModel> getNotAssignedStudents(List<StudentModel> listSiswa);

    List<StudentModel> getNotAssignedStudents();

    List<StudentModel> getListSiswaInKelasX(String idKelas);

    List<MataPelajaranModel> getNotAssignedMatpel(List<MataPelajaranModel> listMatpel);

    List<MataPelajaranModel> getNotAssignedMatpel();

    List<SemesterModel> getAllSemester();

    StudentMataPelajaranModel createStudentMatpel(StudentModel student, MataPelajaranModel matpel);
   
}