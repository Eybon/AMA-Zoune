package provider.infrastructure.factories;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.json.JSONObject;
import provider.api.factories.DataFactory;
import provider.domain.valueobjects.impl.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * BankDataFactory
 * Implementation of DataFactory
 *
 * @version 0.1
 * @author Antoine Forgerou
 * @author Maxime BERGEON
 */
public class ProviderDataFactory implements DataFactory {

    public Data getData(String eventType, String product, int amount) {
        Map<String, Object> fieldsMetadata = new HashMap<String, Object>();

        fieldsMetadata.put("timestamp", new DateTime(DateTimeZone.UTC).toString());
        fieldsMetadata.put("eventType", eventType);

        JSONObject metadata = new JSONObject(fieldsMetadata);

        Map<String, Object> fieldsData = new HashMap<String, Object>();

        fieldsData.put("productReference", product);
        fieldsData.put("amount", amount);

        JSONObject data = new JSONObject(fieldsData);

        return new Data(data.toString(), metadata.toString());
    }

}