package ua.springrest.http.task.two;


/**
 * Пример ответа BODY:
 * {"fio": "Test Testov"}
 */
public class ResponseDecrypted {

    private String fio;

    // {"fio": "sfdjnva9sfv87say9hdfow3"}
    public ResponseDecrypted(String fio) {
        this.fio = fio;
    }

    public String getFio() {
        return fio;
    }

    @Override
    public String toString() {
        return String.format("{\"fio\": \"%s\"}", fio);
    }
}
