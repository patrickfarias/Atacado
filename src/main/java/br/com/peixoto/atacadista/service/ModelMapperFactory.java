package br.com.peixoto.atacadista.service;

import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Getter
@Service
public class ModelMapperFactory {

    private final ModelMapper modelMapper;

    public ModelMapperFactory(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
