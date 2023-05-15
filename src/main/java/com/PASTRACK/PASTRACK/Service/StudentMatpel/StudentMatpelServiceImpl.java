package com.PASTRACK.PASTRACK.Service.StudentMatpel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PASTRACK.PASTRACK.KomponenRequest.getComponent;
import com.PASTRACK.PASTRACK.Model.MataPelajaranModel;
import com.PASTRACK.PASTRACK.Model.PeminatanModel;
import com.PASTRACK.PASTRACK.Model.StudentMataPelajaranModel;
import com.PASTRACK.PASTRACK.Model.StudentModel;
import com.PASTRACK.PASTRACK.Repository.KomponenDB;
import com.PASTRACK.PASTRACK.Repository.MatpelDB;
import com.PASTRACK.PASTRACK.Repository.StudentDB;
import com.PASTRACK.PASTRACK.Repository.StudentKomponenDB;
import com.PASTRACK.PASTRACK.Repository.StudentMatpelDB;
import com.PASTRACK.PASTRACK.Service.Peminatan.PeminatanService;
import com.PASTRACK.PASTRACK.Service.Student.StudentService;

@Service
@Transactional
public class StudentMatpelServiceImpl implements StudentMatpelService {
    @Autowired
    private StudentDB studentDB;

    @Autowired
    private StudentService studentService;

    @Autowired
    private MatpelDB matpelDB;

    @Autowired 
    private StudentKomponenDB studentKomponenDB;

    @Autowired
    private StudentMatpelDB studentMatpelDB;

    @Autowired
    private KomponenDB komponenDB;

    @Autowired 
    private PeminatanService peminatanService;

    @Override
    public StudentMataPelajaranModel generateNilaiStudentMatpel(StudentMataPelajaranModel studentMatpel) {
        StudentModel student = studentMatpel.getStudent();
        MataPelajaranModel matpel = studentMatpel.getMatapelajaran();
        List<getComponent> listAllKomponenSiswa = komponenDB.getAllKomponenSiswa(student, matpel);
        int nilaiMatpel = 0;
        for (getComponent komponen : listAllKomponenSiswa) {
            int calc = komponen.getBobot() * komponen.getNilai() * (1/100);
            nilaiMatpel += calc;
        }
        studentMatpel.setNilai_komponen(nilaiMatpel);
        
        return studentMatpelDB.save(studentMatpel);
    }

    @Override
    public int getNilaiMatpel(StudentMataPelajaranModel studentMatpel) {
        return studentMatpel.getNilai_komponen();
    }

    @Override
    public List<StudentMataPelajaranModel> getListStudentMatpelByPeminatan(String username, String idPpeminatan) {
        Optional<StudentModel> x = studentService.getUserById(username);
        StudentModel student = x.get();
        PeminatanModel peminatan = peminatanService.getPeminatanById(idPpeminatan);
        return studentMatpelDB.findListStudentMatpelByPeminatan(student, peminatan);
    }

    @Override
    public List<StudentMataPelajaranModel> getListStudentMatpelByStudent(String siswaId) {
        StudentModel student = studentService.getById(Long.valueOf(siswaId));
        List<StudentMataPelajaranModel> listStudentMatpel = studentMatpelDB.findListStudentMatpelByStudent(student);
        return listStudentMatpel;
    }

    public List<StudentModel> getStudentsByMataPelajaran(MataPelajaranModel mataPelajaranId) {
        List<StudentModel> students = new ArrayList<>();
        List<StudentMataPelajaranModel> studentMataPelajaranList = studentMatpelDB.getStudentsByMataPelajaran(mataPelajaranId);
        for(StudentMataPelajaranModel studentMataPelajaran : studentMataPelajaranList) {
            students.add(studentMataPelajaran.getStudent());
        }
        return students;
    }

}
