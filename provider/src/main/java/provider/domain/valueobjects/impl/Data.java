package provider.domain.valueobjects.impl;


import provider.api.valueobjects.IData;

/**
 * IData
 * Implementation of the current domain storage format
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