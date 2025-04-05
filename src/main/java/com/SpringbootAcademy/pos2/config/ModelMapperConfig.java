package com.SpringbootAcademy.pos2.config;


import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.description.method.MethodDescription;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {

        return new ModelMapper();
    }
}

//modelMapper.map(thing with data, thing to put data);
//Lise ekak krnne
//
//List<DTO> exampleDTO = modelMapper.map(itemsList, new TypeToken<List<DTO>>(){}.getType());
