package com.PASTRACK.PASTRACK.RestController;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import com.PASTRACK.PASTRACK.Model.PostinganTugasModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.PASTRACK.PASTRACK.Service.Postingan.PostinganService;

@RestController
@CrossOrigin
@RequestMapping("/api/postingan")
public class PostinganController {

    @Autowired
    private PostinganService postinganService;

    //Add

    @GetMapping (value = "/")
    private List<PostinganTugasModel> findAllPostingan (){
        try {
            return postinganService.retrieveListPostingan();
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Postingan not found"
            );
        }
    }

    @PostMapping(value="/")
    private PostinganTugasModel createPostingan(@Valid @RequestBody PostinganTugasModel postingan, BindingResult bindingResult) {
        if(bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field."
            );
        }
        else {
            return postinganService.addPostingan(postingan);
        }
    }

    //Retrieve
    @GetMapping (value = "/{kode}")
    private PostinganTugasModel retrievePostingan (@PathVariable("kode") Long kodePostingan){
        try {
            return postinganService.getPostinganByCode(kodePostingan);
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Postingan " + kodePostingan + " not found"
            );
        }
    }

    //Update
    @PutMapping(value = "/{kode}")
    private PostinganTugasModel updatePostingan (@PathVariable("kode")Long kodePostingan, @RequestBody PostinganTugasModel postingan){
        try{
            return postinganService.updatePostingan(kodePostingan, postingan);
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Postingan " + kodePostingan + " not found"
            );
        }
    }

    //Delete
    @DeleteMapping (value = "/{kode}")
    private ResponseEntity deletePostingan (@PathVariable("kode") Long kodePostingan){
        try {
            postinganService.deletePostingan(kodePostingan);
            return ResponseEntity.ok("Postingan dengan kode " + kodePostingan + " has been deleted successfully");
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Postingan " + kodePostingan + " not found"
            );
        } catch (UnsupportedOperationException e){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,"Tanggal Deadline postingan masih berlaku"
            );
        }
    }
}
