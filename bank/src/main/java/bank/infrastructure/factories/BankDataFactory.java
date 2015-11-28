package bank.infrastructure.factories;

import bank.api.factories.DataFactory;
import bank.domain.valueobjects.impl.Data;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.json.JSONObject;

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
public class BankDataFactory implements DataFactory {

    public Data getData(String eventType, double amount, boolean state, double previousCredit, double newCredit) {
        Map<String, Object> fieldsMetadata = new HashMap<String, Object>();

        fieldsMetadata.put("timestamp", new DateTime(DateTimeZone.UTC).toString());
        fieldsMetadata.put("eventType", eventType);

        JSONObject metadata = new JSONObject(fieldsMetadata);

        Map<String, Object> fieldsData = new HashMap<String, Object>();

        fieldsData.put("amount", amount);
        fieldsData.put("isValid", state);
        fieldsData.put("previousCredit", previousCredit);
        fieldsData.put("newCredit", newCredit);

        JSONObject data = new JSONObject(fieldsData);

        return new Data(data.toString(), metadata.toString());
    }

}