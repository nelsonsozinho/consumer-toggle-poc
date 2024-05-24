package br.shire42.consume.service;

import br.shire42.consume.cliente.FeatureToggleClient;
import br.shire42.consume.cliente.response.FeatureResponse;
import br.shire42.consume.service.model.FeatureToggleRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeatureToggleService {

    private final FeatureToggleClient client;

    public List<FeatureResponse> listFeatureToggleByApplication(final FeatureToggleRequest request) {
        var response = client.getFeatureToggle(request.getAuthorized(), request.getApplicationId());
        return response.getBody();
    }

}
