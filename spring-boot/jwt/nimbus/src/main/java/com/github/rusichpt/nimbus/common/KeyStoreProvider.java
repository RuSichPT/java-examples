package com.github.rusichpt.nimbus.common;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Getter
@Component
public class KeyStoreProvider {

    private RSAPrivateKey privateKey;
    private final Map<String, RSAPublicKey> publicKeys = new HashMap<>();

    @Value("${security.keystore.path}")
    private String keystorePath;

    @Value("${security.keystore.password}")
    private String keystorePassword;

    @Value("${security.keystore.our-key-alias}")
    private String ourKeyAlias;

    @PostConstruct
    void init() {
        loadKeys(keystorePath, keystorePassword, ourKeyAlias);
    }

    private void loadKeys(String path, String password, String keyAlias) {
        try (InputStream is = new ClassPathResource(path).getInputStream()) {
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(is, password.toCharArray());

            Key key = keyStore.getKey(keyAlias, password.toCharArray());
            if (!(key instanceof RSAPrivateKey)) {
                throw new IllegalStateException("Private key not found for alias: " + keyAlias);
            }
            this.privateKey = (RSAPrivateKey) key;

            Enumeration<String> aliases = keyStore.aliases();
            while (aliases.hasMoreElements()) {
                String alias = aliases.nextElement();
                Certificate cert = keyStore.getCertificate(alias);

                if (cert != null) {
                    this.publicKeys.put(alias, ((RSAPublicKey) cert.getPublicKey()));
                }
            }
            log.info("Loaded private key [{}] and {} public keys from keystore",
                    keyAlias, publicKeys.size());
        }
        catch (Exception e) {
            throw new IllegalStateException("Failed to load keystore from path=" + path + ", alias=" + keyAlias, e);
        }
    }
}
