package com.example.DeltaSigma.service;

import com.example.DeltaSigma.dto.DeltaDto;
import com.example.DeltaSigma.exception.DuplicateIdException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeltaService {

    List <DeltaDto >data = new ArrayList<>();
    public DeltaDto addData(DeltaDto add)
    {
        for (DeltaDto d : data) {
            if (d.getId() == add.getId()) {
                throw new DuplicateIdException("ID already exists: " + add.getId());
            }
        }
        DeltaDto dto = new DeltaDto();
        dto.setId(add.getId());
        dto.setName(add.getName());
        dto.setEmail(add.getEmail());
        dto.setDiscription(add.getDiscription());
        data.add(dto);
        return  dto;
    }
    public DeltaDto getByID(int id)
    {

for(DeltaDto d:data)
{
    if(d.getId()==(id))
    {
        return d;
    }
}
return null;
    }
}
