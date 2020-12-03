package ua.springrest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Component;

@Component
public class Aes {

    Logger log = LoggerFactory.getLogger("Aes");

    public Aes() {
        log.info(String.format("Aes class started. password: %s. salt: %s.", password, salt));
    }

    final String password = "f2e102163e8c0c5efb9b912a816ba9bb58fc9afa1";
    // todo вынести в конфиг
    String salt = "d513855667a43400"; // String salt = KeyGenerators.string().generateKey();
    TextEncryptor encryptor = Encryptors.text(password, salt);

    public String encrypt(String textToEncrypt) {
        String textEncrypted = encryptor.encrypt(textToEncrypt);
        log.info(String.format("Called encrypt/1 textToEncrypt: %s. textEncrypted: %s", textToEncrypt, textEncrypted));
        return textEncrypted;
    }

    public String decrypt(String textToDecrypt) {
        String decryptedText;
        try {
            decryptedText = encryptor.decrypt(textToDecrypt);
        } catch (IllegalStateException | IllegalArgumentException ignored) {
            decryptedText = "Error: " + (ignored.getMessage() != null ? ignored.getMessage() : "Wrong text");
        }
        log.info(String.format("Called decrypt/1 textToDecrypt: %s. decryptedText: %s", textToDecrypt, decryptedText));
        return decryptedText;
    }

}