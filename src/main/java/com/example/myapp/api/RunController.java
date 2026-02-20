package com.example.myapp.api;

import com.example.myapp.service.LegacyAppService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RunController {

    private final LegacyAppService legacyAppService;

    public RunController(LegacyAppService legacyAppService) {
        this.legacyAppService = legacyAppService;
    }

    // Option A: POST with JSON body
    @PostMapping("/run")
    public RunResponse run(@Valid @RequestBody RunRequest request) {
        String result = legacyAppService.run(request.input1(),request.input2());
        return new RunResponse(result);
    }

    // Option B: GET with query param (useful for quick tests)
    @GetMapping("/run")
    public RunResponse runGet(@RequestParam @NotBlank String input1,@RequestParam @NotBlank String input2) {
        String result = legacyAppService.run(input1,input2);
        return new RunResponse(result);
    }

    public record RunRequest(@NotBlank String input1,@NotBlank String input2) {}
    public record RunResponse(String result) {}
}