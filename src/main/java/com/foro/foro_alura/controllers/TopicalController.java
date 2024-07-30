package com.foro.foro_alura.controllers;

import com.foro.foro_alura.dto.TopicalDTO;
import com.foro.foro_alura.services.interfaces.ITopicalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/foro")
public class TopicalController {
    @Autowired
    private ITopicalService topicalService;

    @PostMapping("/topicos")
    public ResponseEntity<?> saveTopical(@RequestBody @Valid TopicalDTO topicalDTO){
        try {
            return new ResponseEntity<>(this.topicalService.saveTopical(topicalDTO), HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/topicos")
    public ResponseEntity<List<TopicalDTO>> findAll(){
        return new ResponseEntity<>(topicalService.findAll(), HttpStatus.OK);
    }
    @PutMapping("/topicos/{id}")
    public ResponseEntity<?> updateTopical(@PathVariable long id,@RequestBody TopicalDTO topicalDTO){
       try {
           return new ResponseEntity<>(this.topicalService.updateTopical(id, topicalDTO), HttpStatus.CREATED);
       }catch (IllegalArgumentException e){
           return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
       }
    }
}
