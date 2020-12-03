package ua.springrest;

import javax.persistence.*;

@Entity
@Table(name = "clients")
class Client {
    @Id
    private int    id;
    private String fio;

    public Client() {}

    public Client(int id, String fio) {
        super();
        this.id = id;
        this.fio = fio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    @Override
    public String toString() {
        return "Client {id = " + id + ", fio = '" + fio + "'}";
    }
}