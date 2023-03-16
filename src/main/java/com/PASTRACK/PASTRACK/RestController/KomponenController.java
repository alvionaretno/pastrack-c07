// package com.PASTRACK.PASTRACK.RestController;

// import javax.validation.Valid;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.validation.BindingResult;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.server.ResponseStatusException;

// import com.PASTRACK.PASTRACK.KomponenRequest.addKomponenRequest;
// import com.PASTRACK.PASTRACK.Model.KomponenModel;
// import com.PASTRACK.PASTRACK.Service.Komponen.KomponenService;

// @RestController
// @CrossOrigin
// @RequestMapping("/api/matpel")
// public class KomponenController {
//     @Autowired
//     private KomponenService komponenService;

//     // Create
//     @PostMapping(value = "/{id}/komponen")
//     @PreAuthorize("hasRole('GURU')")
//     private KomponenModel createKomponen(@PathVariable("id") String id, @Valid @RequestBody addKomponenRequest komponen, BindingResult bindingResult) {
//         System.out.println("MASUKK GAK YA");
//         if (bindingResult.hasFieldErrors()) {
//             System.out.println("MASUK if");
//             throw new ResponseStatusException(
//                 HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field."
//             );
//         } else {
//             System.out.println("MASUK ELSE");
//             System.out.println(komponen.getNamaKomponen());
//             System.out.println(komponen.getDeskripsi());
//             System.out.println(komponen.getBobot());
//             System.out.println(komponen.getDueDate());
//             // KomponenModel komponenModel = komponenService.createKomponen(id, komponen);
//             // System.out.println("Kode: " + komponenModel.getKode());
//             // System.out.println("Title: " + komponenModel.getTitle());
//             // System.out.println("desc: " + komponenModel.getDescription());
//             // System.out.println("isreleased: " + komponenModel.getIsReleased());
//             // System.out.println("duedate: " + komponenModel.getDueDate());
//             // System.out.println("akhirtahunajaran: " + komponenModel.getAkhirTahunAjaran());
//             // System.out.println("awaltahunajaran: " + komponenModel.getAwalTahunAjaran());
//             // System.out.println("mata pelajaran: " + komponenModel.getMatapelajaran().getNamaMataPelajaran());
//             // System.out.println("listNilai:" + komponenModel.getListNilai());
//             return komponenService.createKomponen(id, komponen);
//         }
//     }
// }
