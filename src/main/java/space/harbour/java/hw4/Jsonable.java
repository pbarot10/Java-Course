import javax.json.JsonObject;

public interface Jsonable {

    JsonObject toJsonObject();
    String toJsonString();
}