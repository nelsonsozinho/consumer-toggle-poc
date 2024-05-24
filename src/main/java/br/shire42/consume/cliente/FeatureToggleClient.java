package br.shire42.consume.cliente;

import br.shire42.consume.cliente.base.Constants;
import br.shire42.consume.cliente.response.FeatureResponse;
import br.shire42.consume.cliente.response.ToggleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "feature-toggle-service", url = "${service.feature-toggle.api.url}")
public interface FeatureToggleClient {

    @GetMapping(value = "api/feature/{featureId}")
    ResponseEntity<List<FeatureResponse>> getFeatureToggle(
            @RequestHeader(value = Constants.HEADER_AUTHORIZATION) final String authorization,
            @PathVariable("featureId") String featureId
    );

}
