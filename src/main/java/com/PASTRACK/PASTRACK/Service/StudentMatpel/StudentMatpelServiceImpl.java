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
import com.PASTRACK.PASTRACK.Model.SemesterModel;
import com.PASTRACK.PASTRACK.Model.StudentMataPelajaranModel;
import com.PASTRACK.PASTRACK.Model.StudentModel;
import com.PASTRACK.PASTRACK.MuridMatpelRequest.getStudentMatpelByPeminatan;
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
            int bobot = komponen.getBobot();
            int nilai = komponen.getNilai();
            int calc = ((bobot * nilai)/100);
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
    public List<getStudentMatpelByPeminatan> getListStudentMatpelByPeminatan(String username, String idPeminatan) {
        Optional<StudentModel> x = studentService.getUserById(username);
        StudentModel student = x.get();
        PeminatanModel peminatan = peminatanService.getPeminatanById(idPeminatan);
        List<StudentMataPelajaranModel> listStudentMatpelByPeminatan =  studentMatpelDB.findListStudentMatpelByPeminatan(student, peminatan);
        List<getStudentMatpelByPeminatan> newList = new ArrayList<>();
        for (StudentMataPelajaranModel sm : listStudentMatpelByPeminatan) {
            getStudentMatpelByPeminatan req = new getStudentMatpelByPeminatan(sm.getId(), sm.getNilai_komponen(), Long.parseLong(sm.getStudent().getId()), username, sm.getMatapelajaran().getId(), sm.getMatapelajaran().getNamaMataPelajaran());
            newList.add(req);
        }
        return newList;
    }

    @Override
    public List<StudentMataPelajaranModel> getListStudentMatpelByStudent(String siswaId) {
        StudentModel student = studentService.getById(Long.valueOf(siswaId));
        List<StudentMataPelajaranModel> listStudentMatpel = studentMatpelDB.findListStudentMatpelByStudent(student);
        return listStudentMatpel;
    }

    @Override
    public List<StudentModel> getStudentsByMataPelajaran(MataPelajaranModel mataPelajaranId) {
        List<StudentModel> students = new ArrayList<>();
        List<StudentMataPelajaranModel> studentMataPelajaranList = studentMatpelDB.getStudentsByMataPelajaran(mataPelajaranId);
        for(StudentMataPelajaranModel studentMataPelajaran : studentMataPelajaranList) {
            students.add(studentMataPelajaran.getStudent());
        }
        return students;
    }

    @Override
    public List<StudentMataPelajaranModel> getListStudentMatpelByPeminatan(String namaPeminatan, StudentModel student) {
        List<StudentMataPelajaranModel> listSMInPeminatan = new ArrayList<>();
        List<StudentMataPelajaranModel> listAllSM = student.getNilaiAkhir();
        for (StudentMataPelajaranModel sm : listAllSM) {
            if (sm.getMatapelajaran().getPeminatan().getNamaPeminatan().equals(namaPeminatan)) {
                listSMInPeminatan.add(sm);
            }
        }
        return listSMInPeminatan;
    }

    @Override
    public StudentMataPelajaranModel getStudentMatpel(StudentModel student, MataPelajaranModel matpel) {
        return studentMatpelDB.findStudentMatpel(student, matpel);
    }

    @Override
    public List<StudentMataPelajaranModel> getListStudentMatpelBySemester(SemesterModel semester, String username) {
        Optional<StudentModel> x = studentService.getUserById(username);
        StudentModel student = x.get();
        return studentMatpelDB.findListStudentMatpelBySemester(semester, student);
    }
}
