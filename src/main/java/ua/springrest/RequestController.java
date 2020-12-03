package ua.springrest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.springrest.http.task.one.*;
import ua.springrest.http.task.two.*;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;


@RestController
public class RequestController {

    private final AtomicLong counter = new AtomicLong();
    Logger log = LoggerFactory.getLogger("RequestController");
    Logger logRequestResponse = LoggerFactory.getLogger("Http");
    static final Date startDate = new Date();

    @Autowired
    Aes aes;

    @Autowired
    ClientsDAO dao;


    /**
     * 1. Endpoint - шифрование запроса.
     * <p>
     * Принимает на вход id пользователя, шифрует его ФИО (ФИО брать из бд) и возвращает в ответе.
     * Пример запроса BODY:
     * {"id": 1}
     * Пример ответа BODY:
     * {"fio_encr": "sfdjnva9sfv87say9hdfow3"}
     *
     * Curl
     * curl -X POST -H "Content-Type: application/json" --data '{"id":1}' http://localhost:8080/get/fio/encrypted/by/id
     * curl -X POST -H "Content-Type: application/json" --data '{"id":1}' http://ec2-18-191-182-109.us-east-2.compute.amazonaws.com:8080/get/fio/encrypted/by/id
     */
    @PostMapping("/get/fio/encrypted/by/id")
    public ResponseEncrypted encrypt(@RequestBody RequestForEncrypt body) {
        logRequestResponse.info(String.format("Request %s: body= %s", counter.incrementAndGet(), body));
        String fioEncrypted = dao.getClientFioEncrypted(body.id);
        ResponseEncrypted resp = new ResponseEncrypted(fioEncrypted);
        logRequestResponse.info(String.format("Response %s: body= %s", counter, resp.toString()));
        return resp;
    }

    /**
     * 2. Endpoint - дешифрование запроса.
     * <p>
     * Принимает на вход зашифрованную строку с ФИО, на выходе дешифрованная строка с ФИО.
     * Пример запроса BODY:
     * {"fio_encr": "sfdjnva9sfv87say9hdfow3"}
     * Пример ответа BODY:
     * {"fio": "Test Testov"}
     *
     * Curl:
     * curl -X POST -H "Content-Type: application/json" --data '{"fio_encr": "0f8e2a1339520ecb78407fdf3639acb432f09fbc5df72569a9c7b014b0b330db819cf870da075de3b674ac2bcf1fa57fcb477c7c549e2aeb3430297b2c5731e5c016eacc20ebe87c6f6f5d5b66b89b23"}' http://localhost:8080/get/fio/decrypted/by/id
     * curl -X POST -H "Content-Type: application/json" --data '{"fio_encr": "0f8e2a1339520ecb78407fdf3639acb432f09fbc5df72569a9c7b014b0b330db819cf870da075de3b674ac2bcf1fa57fcb477c7c549e2aeb3430297b2c5731e5c016eacc20ebe87c6f6f5d5b66b89b23"}' http://ec2-18-191-182-109.us-east-2.compute.amazonaws.com:8080/get/fio/decrypted/by/id
     */
    @PostMapping("/get/fio/decrypted/by/id")
    public ResponseDecrypted decrypt(@RequestBody RequestForDecrypt body) {
        logRequestResponse.info(String.format("Request %s: body= %s", counter.incrementAndGet(), body));
        ResponseDecrypted resp = new ResponseDecrypted(aes.decrypt(body.fioEncr));
        logRequestResponse.info(String.format("Response %s: body= %s", counter, resp.toString()));
        return resp;
    }

    /**
     *
     * curl http://localhost:8080/get/request/count
     * curl http://ec2-18-191-182-109.us-east-2.compute.amazonaws.com:8080/get/request/count
     *
     * @return "{\"count\": 100500}"
     */
    @GetMapping("/get/request/count")
    public String test() {
        return String.format("{\"count\": %s}", counter.incrementAndGet());
    }

    /**
     *
     * curl http://localhost:8080/get/uptime
     * curl http://ec2-18-191-182-109.us-east-2.compute.amazonaws.com:8080/get/uptime
     *
     * @return "{\"uptime\": \"0 days, 0 hours, 0 minutes, 43 seconds\"}"
     */
    @GetMapping("/get/uptime")
    public String uptime() {
        counter.incrementAndGet();
        return String.format("{\"uptime\": \"%s\"}", getElapsed(startDate, new Date()));
    }

    private String getElapsed(Date startDate, Date endDate){
        //milliseconds
        long different = endDate.getTime() - startDate.getTime();
        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        return String.format("%s days, %s hours, %s minutes, %s seconds", elapsedDays, elapsedHours, elapsedMinutes, elapsedSeconds);

    }

}