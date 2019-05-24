/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alura.alurator.protocolo;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author marcosparreira
 */
public class QueryParamsBuilder {

    private Map<String, Object> queryParams = new HashMap<>();

    public QueryParamsBuilder withParams(String stringQueryParams) {
        String[] stringParams = stringQueryParams.split("&");

        for (String stringParam : stringParams) {
            String[] chaveEValor = stringParam.split("=");

            String chave = chaveEValor[0];
            Object valor = chaveEValor[1];

            queryParams.put(chave, valor);
        }

        return this;
    }

    public Map<String, Object> build() {
        return this.queryParams;
    }
}
