import com.PASTRACK.PASTRACK.DashboardSiswaRequest.*;
import com.PASTRACK.PASTRACK.MuridMatpelRequest.getStudentMatpelByPeminatan;
import com.PASTRACK.PASTRACK.PeminatanRequest.PeminatanResponse;
import com.PASTRACK.PASTRACK.Service.DashboardSiswa.DashboardSiswaService;
import com.PASTRACK.PASTRACK.Service.Peminatan.PeminatanService;
import com.PASTRACK.PASTRACK.Service.StudentMatpel.StudentMatpelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin
@RequestMapping("/api/dashboard/siswa")
public class DashboardSiswaController {
    @Autowired
    private DashboardSiswaService dashboardSiswaService;

    @Autowired
    private StudentMatpelService studentMatpelService;

    @Autowired
    private PeminatanService peminatanService;

    @GetMapping(value = "/nilai-per-semester/{username}")
    @PreAuthorize("hasRole('SISWA')")
    private List<StudentScoreDTO> getNilaiEachSemester(@PathVariable("username") String usernameSiswa, Principal principal) {
        try {
            return dashboardSiswaService.getStudentScoresBySemester(usernameSiswa);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "not found"
            );
        }
    }

    // @GetMapping(value = "/{username}")
    // @PreAuthorize("hasRole('SISWA')")
    // private AllDashboard getRataRataAllMatpel(@PathVariable("username") String usernameSiswa, Principal principal) {
    //     try {
    //         dashboardSiswaService.generateAllNilaiMatpel(usernameSiswa);
    //         return dashboardSiswaService.getAllViewed(usernameSiswa);
    //     } catch (NoSuchElementException e) {
    //         throw new ResponseStatusException(
    //             HttpStatus.NOT_FOUND, "not found"
    //         );
    //     }
    // }

    @GetMapping(value = "/{username}/{idPeminatan}")
    @PreAuthorize("hasRole('SISWA')")
    private List<getStudentMatpelByPeminatan> listStudentMatpelByPeminatan(@PathVariable("username") String usernameSiswa, @PathVariable("idPeminatan") String idPeminatan, Principal principal) {
        try {
            return studentMatpelService.getListStudentMatpelByPeminatan(usernameSiswa, idPeminatan);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "not found"
            );
        }
    }

    @GetMapping(value = "/peminatan/{username}")
    @PreAuthorize("hasRole('SISWA')")
    private List<PeminatanResponse> listPeminatanInSiswa(@PathVariable("username") String usernameSiswa, Principal principal) {
        try {
            return peminatanService.getListPeminatanInSiswa(usernameSiswa);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "not found"
            );
        }
    }

    // get all ranking siswa
    @GetMapping(value = "/all-ranking/{usernameSiswa}")
    @PreAuthorize("hasRole('SISWA')")
    private allRankingSiswa getAllRankingSiswa(@PathVariable("usernameSiswa") String usernameSiswa, Principal principal) {
        try {
            return dashboardSiswaService.getAllRankingSiswa(usernameSiswa);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "not found"
            );
        }
    }

    //get student's ranking in angkatan
    @GetMapping(value = "/ranking-angkatan/{usernameSiswa}")
    @PreAuthorize("hasRole('SISWA')")
    private int getRankingInAngkatan(@PathVariable("usernameSiswa") String usernameSiswa, Principal principal) {
        try {
            return dashboardSiswaService.getStudentRankingInAngkatan(usernameSiswa);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "not found"
            );
        }
    }

    //get student's ranking in kelas
    @GetMapping(value = "/ranking-kelas/{usernameSiswa}")
    @PreAuthorize("hasRole('SISWA')")
    private int getRankingInKelas(@PathVariable("usernameSiswa") String usernameSiswa, Principal principal) {
        try {
            return dashboardSiswaService.getStudentRankingInKelas(usernameSiswa);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "not found"
            );
        }
    }

    @GetMapping(value = "/{username}/perkembangan")
    @PreAuthorize("hasRole('SISWA')")
    private List<PencapaianNilaiPerMatpel> pencapaianNilai(@PathVariable("username") String usernameSiswa, @Valid @RequestBody pencapaianReq namaPeminatan, Principal principal) {
        try {
            return dashboardSiswaService.getNilaiMatpel(usernameSiswa, namaPeminatan.getNamaPeminatan());
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "not found"
            );
        }
    }

    @GetMapping(value = "/{username}/avg-linechart")
    @PreAuthorize("hasRole('SISWA')")
    private List<PencapaianNilaiAllMatpel> avgLineChart(@PathVariable("username") String usernameSiswa, Principal principal) {
        try {
            return dashboardSiswaService.getNilaiRataRata(usernameSiswa);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "not found"
            );
        }
    }
}