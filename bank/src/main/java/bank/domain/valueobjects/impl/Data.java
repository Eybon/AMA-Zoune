package bank.domain.valueobjects.impl;

import bank.api.valueobjects.IData;

/**
 * Data
 * Implementation of IData layer
 *
 * @version 0.1
 * @author Antoine Forgerou
 * @author Maxime BERGEON
 */
public class Data implements IData {

    String value;
    String metadata;

    public Data(String value,String metaData) {
        this.value = value;
        this.metadata = metaData;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getMetadata() {
        return this.metadata;
    }
}