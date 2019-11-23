package main.java.space.harbour.java.hw7;

import main.java.space.harbour.java.hw4.Jsonable;

import javax.json.Json;
import javax.json.JsonObject;

public final class Director implements Jsonable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(final String n) {
        this.name = n;
    }

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
