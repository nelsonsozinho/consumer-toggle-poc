package br.shire42.consume.service;

import br.shire42.consume.cliente.FeatureToggleClient;
import br.shire42.consume.cliente.response.FeatureResponse;
import br.shire42.consume.service.model.FeatureToggleRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class FeatureToggleService {

    private final FeatureToggleClient client;

    public List<FeatureResponse> listFeatureToggleByApplication(final FeatureToggleRequest request) {
        log.info("New request to get the features from {}", request.getApplicationId());
        var response = client.getFeatureToggle(request.getAuthorized(), request.getApplicationId());
        return response.getBody();
    }

}
