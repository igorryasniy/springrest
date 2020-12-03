package ua.springrest;


import java.util.List;

import org.hibernate.Session;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientsDAO {

    private Logger log = LoggerFactory.getLogger("ClientsDAO");

    @Autowired
    Aes aes;


    /**
     * чтение записей
     */
    private void recordsRead() {
        log.info("Чтение записей таблицы");
        Session session = HiberSessionFactory.getSession();
        List<Client> list = session.createQuery("from Client", Client.class).getResultList();
        log.info(String.valueOf(list));
        HiberSessionFactory.closeSession(session);
    }

    public String getClientFioEncrypted(int id) {
        Session session = HiberSessionFactory.getSession();
        Client client = session.load(Client.class, id);
        log.info(client.toString());
        String fioEncrypted = aes.encrypt(client.getFio());
        HiberSessionFactory.closeSession(session);
        return fioEncrypted;
    }
}