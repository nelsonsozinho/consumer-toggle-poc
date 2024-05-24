package br.shire42.consume.service.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FeatureToggleRequest {

    private String applicationId;
    private String authorized;

}
