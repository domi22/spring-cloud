package spring.cloud.service.test.common.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import org.apache.commons.lang.time.DateUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

public class CommonDateDeserializer extends StdScalarDeserializer {

    public  static final CommonDateDeserializer INSTANCE = new CommonDateDeserializer(Date.class);
    private final String[] DATE_FORMAT_STRS = {"yyyy-MM-dd","yyyy/MM/dd"};

    protected CommonDateDeserializer(Class vc) {
        super(vc);
    }

    @Override
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken t = jsonParser.getCurrentToken();
        if (t == JsonToken.VALUE_STRING) {
            String str = jsonParser.getText().trim();
            if (str.length() == 0) {
                return getEmptyValue(deserializationContext);
            }
            try {
                return DateUtils.parseDate(str,DATE_FORMAT_STRS);
            } catch (ParseException e) {
                return (Date)deserializationContext.handleWeirdStringValue(handledType(),str,"expected format \"%s\"");
            }
        }

        if (t == JsonToken.START_ARRAY && deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
            jsonParser.nextToken();
            final Date parsed = _parseDate(jsonParser,deserializationContext);
            t = jsonParser.nextToken();
            if (t != JsonToken.END_ARRAY) {
                handleMissingEndArrayForSingle(jsonParser,deserializationContext);
            }
            return parsed;
        }
        return super._parseDate(jsonParser,deserializationContext);
    }
}
