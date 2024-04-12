package com.github.rusichpt;

import lombok.SneakyThrows;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

public class MainKeyAgreement {
    @SneakyThrows
    public static void main(String[] args) {
        // 1. Одна из сторон (Алиса) генерирует пару ключей. Encoded публичный ключ отдаёт.
        KeyPairGenerator aliceKpairGen = KeyPairGenerator.getInstance("DH");
        KeyPair aliceKeyPair = aliceKpairGen.generateKeyPair();
        byte[] alicePubKeyEncoded = aliceKeyPair.getPublic().getEncoded();

        // 2. Другая сторона (например, Боб) получает открытый ключ Алисы
        KeyFactory bobKeyFactory = KeyFactory.getInstance("DH");
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(alicePubKeyEncoded);
        PublicKey alicePubKey = bobKeyFactory.generatePublic(x509KeySpec);

        // Параметры, которые использовала Алиса при генерации ключей
        DHParameterSpec dhParamFromAlicePubKey = ((DHPublicKey)alicePubKey).getParams();

        // Создаёт свою пару ключей. Отдаёт свой Encoded открытый ключ
        KeyPairGenerator bobKpairGen = KeyPairGenerator.getInstance("DH");
        bobKpairGen.initialize(dhParamFromAlicePubKey);
        KeyPair bobKeyPair = bobKpairGen.generateKeyPair();
        byte[] bobPubKeyEncoded = bobKeyPair.getPublic().getEncoded();

        // Теперь, у Алисы есть открытый ключ Боба, а у Боба есть открытый ключ Алисы. Что дальше?
        // Как сказано в документации JCA, у нас есть инструмент KeyAgreement, https://docs.oracle.com/javase/8/docs/technotes/guides/security/crypto/CryptoSpec.html#KeyAgreement который позволяет установить одинаковые ключи шифрования без необходимости обмениваться секретной информацией (т.е. без обмена private key). Соглашение выглядит следующим образом:
        // 3. Соглашение по протоколу Диффи-Хеллмана (Diffie–Hellman, DH)
        KeyAgreement aliceKeyAgree = KeyAgreement.getInstance("DH");
        aliceKeyAgree.init(aliceKeyPair.getPrivate());

        // Алиса на основе ключа боба и своего private key создаёт общий shared ключ
        KeyFactory aliceKeyFactory = KeyFactory.getInstance("DH");
        x509KeySpec = new X509EncodedKeySpec(bobPubKeyEncoded);
        PublicKey bobPubKey = aliceKeyFactory.generatePublic(x509KeySpec);
        aliceKeyAgree.doPhase(bobPubKey, true);
        byte[] aliceSharedSecret = aliceKeyAgree.generateSecret();
        SecretKeySpec aliceAesKey = new SecretKeySpec(aliceSharedSecret, 0, 16, "AES");

        // Боб на основе ключа Алисы и своего private key создаёт общий shared ключ
        KeyAgreement bobKeyAgree = KeyAgreement.getInstance("DH");
        bobKeyAgree.init(bobKeyPair.getPrivate());
        bobKeyAgree.doPhase(alicePubKey, true);
        byte[] bobSharedSecret = bobKeyAgree.generateSecret();
        SecretKeySpec bobAesKey = new SecretKeySpec(bobSharedSecret, 0, 16, "AES");

        // Общий ключ у Алисы и Боба одинаков
        System.out.println("Shared keys are equals: " + Arrays.equals(aliceSharedSecret, bobSharedSecret));

        // Далее Боб и Алиса, используя общий ключ, про который больше никто не знает, обмениваются зашифрованными данными:
        // 4. Боб шифрует сообщение для Алисы
        Cipher bobCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        bobCipher.init(Cipher.ENCRYPT_MODE, bobAesKey);
        byte[] ciphertext = bobCipher.doFinal("Hello, Alice!".getBytes());

        // Передаёт Алисе параметры, с которыми выполнялась шифровка
        byte[] encodedParamsFromBob = bobCipher.getParameters().getEncoded();

        // 5. Алиса принимает сообщение и расшифровывает его
        AlgorithmParameters aesParams = AlgorithmParameters.getInstance("AES");
        aesParams.init(encodedParamsFromBob);
        Cipher aliceCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        aliceCipher.init(Cipher.DECRYPT_MODE, aliceAesKey, aesParams);
        byte[] recovered = aliceCipher.doFinal(ciphertext);
        System.out.println(new String(recovered));
    }
}
