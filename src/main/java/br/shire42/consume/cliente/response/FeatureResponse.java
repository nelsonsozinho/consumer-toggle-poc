package br.shire42.consume.cliente.response;

import lombok.Data;

@Data
public class FeatureResponse {

    private String name;
    private String code;
    private Boolean activated;

}
