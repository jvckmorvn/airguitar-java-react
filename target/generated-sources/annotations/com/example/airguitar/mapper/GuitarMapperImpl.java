package com.example.airguitar.mapper;

import com.example.airguitar.model.Guitar;
import com.example.airguitar.model.dto.GuitarDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-15T11:06:36+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.9 (Homebrew)"
)
@Component
public class GuitarMapperImpl implements GuitarMapper {

    @Override
    public Guitar toEntity(GuitarDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Guitar guitar = new Guitar();

        guitar.id = dto.id;
        guitar.model = dto.model;

        return guitar;
    }

    @Override
    public GuitarDTO toDto(Guitar entity) {
        if ( entity == null ) {
            return null;
        }

        GuitarDTO guitarDTO = new GuitarDTO();

        guitarDTO.id = entity.id;
        guitarDTO.model = entity.model;

        return guitarDTO;
    }
}
