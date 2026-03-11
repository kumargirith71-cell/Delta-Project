package com.example.DeltaSigma.controller;

import com.example.DeltaSigma.dto.DeltaDto;
import com.example.DeltaSigma.service.DeltaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delta")
@CrossOrigin("*")
public class DeltaController {
    @Autowired
private DeltaService deltaService;
    @PostMapping("/add")
    public DeltaDto add(@Valid @RequestBody DeltaDto add)
    {
      return  deltaService.addData(add);
    }
    @GetMapping("/get/{id}")
    public DeltaDto getByID( @Valid @PathVariable int id)
    {
        return deltaService.getByID(id);
    }
}
