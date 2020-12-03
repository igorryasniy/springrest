package ua.springrest.http.task.two;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
public class RequestForDecrypt {
    @JsonProperty(value = "fio_encr")
    public String fioEncr;

    public RequestForDecrypt(String fioEncr) {
        this.fioEncr = fioEncr;
    }

    // Без этого конструктора JSON-ина с одним параметром вызывает ошибку (wtf!?)
    public RequestForDecrypt() {
    }

    @Override
    public String toString() {
        return String.format("{\"fio_encr\":%s}", fioEncr);
    }
}