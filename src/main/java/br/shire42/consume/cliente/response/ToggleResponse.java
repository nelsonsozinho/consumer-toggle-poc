package br.shire42.consume.cliente.response;

import lombok.Data;

import java.util.List;

@Data
public class ToggleResponse {

    private List<FeatureResponse> featuresResponse;

}
