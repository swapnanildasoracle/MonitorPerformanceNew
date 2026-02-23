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
        String result = legacyAppService.run(request.url(),request.token());
        return new RunResponse(result);
    }

    // Option B: GET with query param (useful for quick tests)
    @GetMapping("/run")
    public RunResponse runGet(@RequestParam @NotBlank String url,@RequestParam @NotBlank String token) {
        String result = legacyAppService.run(url,token);
        return new RunResponse(result);
    }

    public record RunRequest(@NotBlank String url,@NotBlank String token) {}
    public record RunResponse(String result) {}
}