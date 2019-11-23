package main.java.space.harbour.java.hw4;

import javax.json.Json;
import javax.json.JsonObject;

public final class Director implements Jsonable {
    private String name;

    public void fromJsonObject(final JsonObject jsonObject) {
        this.name = jsonObject.getString("Name");
    }

    @Override
    public JsonObject toJsonObject() {
        return Json.createObjectBuilder()
                .add("Name", name)
                .build();
    }

    @Override
    public String toJsonString() {
        return toJsonObject().toString();
    }
}
