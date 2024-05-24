package br.shire42.consume.controller;

import br.shire42.consume.cliente.response.FeatureResponse;
import br.shire42.consume.cliente.response.ToggleResponse;
import br.shire42.consume.service.FeatureToggleService;
import br.shire42.consume.service.model.FeatureToggleRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/application")
@RequiredArgsConstructor
public class FeatureController {

    private final FeatureToggleService service;

    @GetMapping("/{applicationId}/feature")
    public ResponseEntity<List<FeatureResponse>> listAllFeaturesFromApplication(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
            @PathVariable("applicationId") String applicationId
    ) {
        FeatureToggleRequest request = FeatureToggleRequest.builder()
                .applicationId(applicationId)
                .authorized(authorization)
                .build();

        return ResponseEntity.ok(service.listFeatureToggleByApplication(request));
    }

}
