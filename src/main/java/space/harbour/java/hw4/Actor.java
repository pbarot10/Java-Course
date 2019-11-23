package main.java.space.harbour.java.hw4;

import javax.json.Json;
import javax.json.JsonObject;

public final class Actor implements Jsonable {

    private String name;
    private String as;

    @Override
    public JsonObject toJsonObject() {
        return Json.createObjectBuilder()
                .add("Name", name)
                .add("As", as)
                .build();
    }

    @Override
    public String toJsonString() {
        return toJsonObject().toString();
    }

    public void fromJsonObject(final JsonObject jsonObject) {
        this.name = jsonObject.getString("Name");
        this.as = jsonObject.getString("As");
    }
}
