package ua.springrest.http.task.one;

public class ResponseEncrypted {

    private String fio_encr;

    // {"fio_encr": "sfdjnva9sfv87say9hdfow3"}
    public ResponseEncrypted(String fio_encr) {
        this.fio_encr = fio_encr;
    }

    public String getFio_encr() {
        return fio_encr;
    }

    @Override
    public String toString() {
        return String.format("{\"fio_encr\": \"%s\"}", fio_encr);
    }
}
