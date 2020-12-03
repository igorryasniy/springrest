package ua.springrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestApplication.class, args);
	}

	// для установки уровня логирования из конфига или внеш источника
//	private void setLoggingLevelGlobalToInfo() {
//		ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) org.slf4j.LoggerFactory.getLogger(
//				ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
//        root.setLevel(logger_level.equals("info") ? Level.INFO : Level.DEBUG);
//		root.setLevel(Level.INFO);
//	}

}
