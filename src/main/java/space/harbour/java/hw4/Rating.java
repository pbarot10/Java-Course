ackage main.java.space.harbour.java.hw4;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public final class Rating implements Jsonable {
    private String source;
    private String value;
    private Integer votes;

    @Override
    public JsonObject toJsonObject() {
        JsonObjectBuilder builder = Json.createObjectBuilder()
                .add("Source", source)
                .add("Value", value);

        if (votes != null) {
            builder.add("Votes", votes);
        }

        return builder.build();
    }

    @Override
    public String toJsonString() {
        return toJsonObject().toString();
    }

    public void fromJsonObject(final JsonObject jsonObject) {
        this.source = jsonObject.getString("Source");
        this.value = jsonObject.getString("Value");
        if (jsonObject.containsKey("Votes")) {
            this.votes = jsonObject.getInt("Votes");
        }
    }
}
