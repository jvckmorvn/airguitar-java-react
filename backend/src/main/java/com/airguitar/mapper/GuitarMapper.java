package com.airguitar.mapper;

import com.airguitar.model.Guitar;
import com.airguitar.model.dto.GuitarDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GuitarMapper {

    Guitar toEntity(GuitarDTO dto);

    GuitarDTO toDto(Guitar entity);

}
