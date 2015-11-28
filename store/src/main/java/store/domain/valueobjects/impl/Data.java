package store.domain.valueobjects.impl;


import store.api.valueobjects.IData;

/**
 * Data
 * Data layer for storage usage
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