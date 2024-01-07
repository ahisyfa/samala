package com.casa.samala.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@Data
@Getter
@ConfigurationProperties(prefix = "samala")
public class SamalaConfigProperties {

    private String jwtSecretKey;

    @ConstructorBinding
    public SamalaConfigProperties(String jwtSecretKey) {
        this.jwtSecretKey = jwtSecretKey;
    }

}


