package ua.springrest.http.task.one;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
public class RequestForEncrypt {
    @JsonProperty(value = "id")
    public int id;

    public RequestForEncrypt(int id) {
        this.id = id;
    }

    // Без этого конструктора JSON-ина с одним параметром вызывает ошибку (wtf!?)
    public RequestForEncrypt() {
    }

    @Override
    public String toString() {
        return String.format("{\"id\":%s}", id);
    }
}