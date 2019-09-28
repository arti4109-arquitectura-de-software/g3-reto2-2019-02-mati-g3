package com.atpos.api.business.builder;

import com.atpos.api.commons.util.Utils;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;
import java.util.Date;

@Configuration
public class mapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper =  new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.addConverter(ctx -> ctx.getSource() != null
                ? Utils.convertToString(ctx.getSource())
                : null, Date.class, String.class);

        modelMapper.addConverter(ctx -> ctx.getSource() != null
                ? Utils.stringToDateOrNull(ctx.getSource())
                : null, String.class, Date.class);

        modelMapper.addConverter(ctx -> ctx.getSource() != null
                ? Utils.convertToString(ctx.getSource())
                : null, Timestamp.class, String.class);

        return modelMapper;
    }
}
