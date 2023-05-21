package com.PASTRACK.PASTRACK.Service.Semester;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import com.PASTRACK.PASTRACK.Model.SemesterModel;
import com.PASTRACK.PASTRACK.Repository.SemesterDB;
import com.PASTRACK.PASTRACK.SemesterRequest.addSemesterRequest;
import com.PASTRACK.PASTRACK.SemesterRequest.addSemesterResponse;
import com.PASTRACK.PASTRACK.Service.Semester.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PASTRACK.PASTRACK.Model.RoleModel;
import com.PASTRACK.PASTRACK.Repository.RoleDB;

@Service
@Transactional
public class SemesterServiceImpl implements SemesterService {

    @Autowired
    SemesterDB semesterDb;

    @Override
    public List<SemesterModel> findAll() {

        return semesterDb.findAll();

    }
    @Override
    public SemesterModel getSemesterById(Long id) {
        Optional<SemesterModel> semester = semesterDb.findById(id);
        if (semester.isPresent()) {
            return semester.get();
        }
        return null;
    }

    @Override
    public addSemesterResponse createSemester(addSemesterRequest semesterRequest) {
        SemesterModel semester = new SemesterModel();
        semester.setSemester(semesterRequest.isSemester());
        semester.setAwalTahunAjaran(semesterRequest.getAwalTahunAjaran());
        semester.setAkhirTahunAjaran(semesterRequest.getAkhirTahunAjaran());
        semesterDb.save(semester);
        addSemesterResponse semesterResp = new addSemesterResponse(semester.getId(), semester.getSemester(), semester.getAwalTahunAjaran(), semester.getAkhirTahunAjaran());
        return semesterResp;
    }

    public SemesterModel getCurrentSemester() {
        LocalDateTime currentDate = LocalDateTime.now();

        List<SemesterModel> semesters = semesterDb.findAll();
        for (SemesterModel semester : semesters) {
            LocalDateTime startDate = semester.getAwalTahunAjaran();
            LocalDateTime endDate = semester.getAkhirTahunAjaran();

            // Check if the current date falls within the start and end dates of the semester
            if (currentDate.isAfter(startDate) && currentDate.isBefore(endDate)) {
                return semester;
            }
        }

        throw new NoSuchElementException("No current semester found");
    }

}
