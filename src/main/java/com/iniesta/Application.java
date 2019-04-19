package com.iniesta;

import javax.inject.Singleton;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
		info = @Info(title = "My Micronaut Samples"))
@Singleton
public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class);
    }
}