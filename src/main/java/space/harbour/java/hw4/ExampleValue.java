package space.harbour.java.hw4;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;

@SuppressWarnings("checkstyle:magicnumber")
public final class ExampleValue implements Jsonable {
    private static Integer i = 3010;
    private static String s = "PARSHAD";
    private static float f = .9f;
    private InsideClass hiddenClass = new InsideClass();

    class InsideClass implements Jsonable {
        private final String s = "BAROT";
        private final Integer i = 1997;

        @Override
        public JsonObject toJsonObject() {
            return Json.createObjectBuilder()
                    .add("s", s)
                    .add("i", i)
                    .build();
        }

        @Override
        public String toJsonString() {
            return toJsonObject().toString();
        }
    }

    @Override
    public JsonObject toJsonObject() {
        return Json.createObjectBuilder()
                .add("i", i)
                .add("s", s)
                .add("f", f)
                .add("hiddenClass", hiddenClass.toJsonObject())
                .build();
    }

    @Override
    public String toJsonString() {
        return toJsonObject().toString();
    }

    public void fromJson(final String json) {
        JsonReader reader = Json.createReader(new StringReader(json));
        JsonObject jObject = reader.readObject();
        this.i = jObject.getInt("i");
        this.s = jObject.getString("s");
        this.f = (float) jObject.getJsonNumber("f").doubleValue();

        this.hiddenClass = new InsideClass();
        //this.hiddenClass.fromJson(jObject
        // .getJsonObject("hiddenClass").toString());
    }

    public static void main(String[] args) {
        ExampleValue value = new ExampleValue();
        System.out.println(value.toJsonString());
    }
}