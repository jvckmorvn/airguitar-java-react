package com.airguitar.mapper;

import com.example.airguitar.model.Guitar;
import com.example.airguitar.model.dto.GuitarDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GuitarMapper {

    Guitar toEntity(GuitarDTO dto);

    GuitarDTO toDto(Guitar entity);

}
