package br.com.educatech.EducaTech.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de Configuração do ModelMapper, dependência utilizada para mapear uma Entidade para um DTO (Data Transfer Object) e
 * vice-versa
 * */
@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
