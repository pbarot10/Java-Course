package main.java.space.harbour.java.hw4;

import javax.json.Json;
import javax.json.JsonObject;

public final class Writer implements Jsonable {

    private String name;
    private String type;

    public void fromJsonObject(final JsonObject jsonObject) {
        this.name = jsonObject.getString("Name");
        this.type = jsonObject.getString("Type");
    }

    @Override
    public JsonObject toJsonObject() {
        return Json.createObjectBuilder()
                .add("Name", name)
                .add("Type", type)
                .build();
    }

    @Override
    public String toJsonString() {
        return toJsonObject().toString();
    }
}
