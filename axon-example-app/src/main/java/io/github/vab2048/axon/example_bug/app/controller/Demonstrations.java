package io.github.vab2048.axon.example_bug.app.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.web.bind.annotation.GetMapping;

@Tags(value = @Tag(name = "Example Demonstrations", description = "Hit an endpoint to demonstrate a specific feature."))
public interface Demonstrations {


    @GetMapping("/demo/trigger-account-snapshot")
    void triggerAccountSnapshot();

}
