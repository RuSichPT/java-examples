package com.github.rusichpt;

import jakarta.xml.bind.DatatypeConverter;
import lombok.SneakyThrows;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import java.security.*;

public class MainJCA {
    public static void main(String[] args) {
        showProviders();
        System.out.println("\n=====================");
        showHash();
        System.out.println("\n=====================");
        System.out.println("Symmetric Cryptography:");
        symmetricCryptographyECB();
        symmetricCryptographyCBC();
        System.out.println("\n=====================");
        System.out.println("Asymmetric  Cryptography:");
        asymmetricCryptography();
        System.out.println("\n=====================");
        System.out.println("Digital Signature:");
        digitalSignature();
    }

    public static void showProviders() {
        System.out.println("Providers: ");
        Provider[] providers = Security.getProviders();
        for (Provider p : providers) {
            System.out.println(p.getName());
        }
    }

    public static void showHash() {
        try {
            String algorithm = "SHA-512";
            System.out.printf("Hash %s:%n", algorithm);
            MessageDigest digester = MessageDigest.getInstance(algorithm);
            byte[] input = "Secret string".getBytes();
            byte[] salt = new byte[16];
            SecureRandom.getInstanceStrong().nextBytes(salt);
            digester.update(salt);
            byte[] digest = digester.digest(input);
            System.out.println(DatatypeConverter.printHexBinary(digest));
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    @SneakyThrows
    public static void symmetricCryptographyECB() {
        String text = "secret!!secret!!secret!!secret!!"; // 32 байта
        // Generate new key
        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        keygen.init(256); // размер блока 32 байта
        Key key = keygen.generateKey();

        // Encrypt with key
        String transformation = "AES/ECB/PKCS5Padding"; //размер блока 16 байт
        Cipher cipher = Cipher.getInstance(transformation);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(text.getBytes());
        System.out.println("ECB: ");
        System.out.println(DatatypeConverter.printHexBinary(encrypted));

        // Decrypt with key
        cipher.init(Cipher.DECRYPT_MODE, key);
        String result = new String(cipher.doFinal(encrypted));
        System.out.println(result); // видим повторение блоков
    }

    @SneakyThrows
    public static void symmetricCryptographyCBC() {
        // Initialization Vector
        SecureRandom random = SecureRandom.getInstanceStrong();
        byte[] rnd = new byte[16];
        random.nextBytes(rnd);
        IvParameterSpec ivSpec = new IvParameterSpec(rnd);

        // Prepare key
        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        keygen.init(256);
        Key key = keygen.generateKey();

        // CBC
        String text = "secret!!secret!!secret!!secret!!";
        String transformation = "AES/CBC/PKCS5Padding";
        Cipher cipher = Cipher.getInstance(transformation);
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
        byte[] enc = cipher.doFinal(text.getBytes());
        System.out.println("CBC: ");
        System.out.println(DatatypeConverter.printHexBinary(enc));

        // Decrypt
        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
        String result = new String(cipher.doFinal(enc));
        System.out.println(result);
    }

    @SneakyThrows
    public static void asymmetricCryptography() {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(1024);
        KeyPair keyPair = generator.generateKeyPair();
        System.out.println("Public key:");
        System.out.println(DatatypeConverter.printHexBinary(keyPair.getPublic().getEncoded()));
        System.out.println("Private key:");
        System.out.println(DatatypeConverter.printHexBinary(keyPair.getPrivate().getEncoded()));
        System.out.println();

        // Encrypt with PRIVATE KEY
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
        byte[] enc = cipher.doFinal("Hello!".getBytes());
        System.out.println(DatatypeConverter.printHexBinary(enc));

        // Decrypt with PUBLIC KEY
        cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
        byte[] result = cipher.doFinal(enc);
        System.out.println(new String(result));
    }

    @SneakyThrows
    public static void digitalSignature() {
        // Generate keys
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        SecureRandom random = SecureRandom.getInstanceStrong();
        generator.initialize(2048, random);
        KeyPair keyPair = generator.generateKeyPair();

        // Digital Signature
        Signature dsa = Signature.getInstance("SHA256withRSA");
        dsa.initSign(keyPair.getPrivate());

        // Update and sign the data
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
        byte[] enc = cipher.doFinal("Hello!".getBytes());
        dsa.update(enc);
        byte[] signature = dsa.sign();

        // Verify signature
        dsa.initVerify(keyPair.getPublic());
        dsa.update(enc);
        boolean verifies = dsa.verify(signature);
        System.out.println("Signature is ok: " + verifies);

        // Decrypt if signature is correct
        if (verifies) {
            cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
            byte[] result = cipher.doFinal(enc);
            System.out.println(new String(result));
        }
    }
}