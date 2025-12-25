package com.github.rusichpt.nimbus.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.security.interfaces.RSAPublicKey;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtFacade {

    private final KeyStoreProvider keyStoreProvider;
    private final JsonFacade jsonFacade;

    public <T> T verifyAndGetClaims(String jwtCompact, TypeReference<T> valueTypeRef) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(jwtCompact);

            boolean verified = false;
            for (Map.Entry<String, RSAPublicKey> entry : keyStoreProvider.getPublicKeys().entrySet()) {
                JWSVerifier verifier = new RSASSAVerifier(entry.getValue());
                if (signedJWT.verify(verifier)) {
                    verified = true;
                    break;
                }
            }

            if (!verified) {
                throw new SecurityException("Invalid JWS signature");
            }

            return jsonFacade.readValue(signedJWT.getPayload().toString(), valueTypeRef);

        } catch (Exception e) {
            throw new SecurityException("JWS verification failed", e);
        }
    }

    public <T> T verifyAndGetClaims(String jwsCompact, Class<T> tClass) {
        return verifyAndGetClaims(jwsCompact, new TypeReference<>() {
            @Override
            public Type getType() {
                return tClass;
            }
        });
    }

    public <T> String sign(T payload) {
        try {
            String payloadJson = jsonFacade.writeValueAsString(payload);

            Payload jwsPayload = new Payload(payloadJson);

            JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.RS512)
                    .type(JOSEObjectType.JWT)
                    .build();

            JWSObject jwsObject = new JWSObject(header, jwsPayload);

            JWSSigner signer = new RSASSASigner(keyStoreProvider.getPrivateKey());
            jwsObject.sign(signer);

            return jwsObject.serialize();
        } catch (Exception e) {
            throw new IllegalStateException("Failed to generate JWT", e);
        }
    }

    public String generateJwt(Map<String, Object> claims) {
        try {
            JWTClaimsSet.Builder claimsBuilder = new JWTClaimsSet.Builder();

            claims.forEach(claimsBuilder::claim);

            JWTClaimsSet claimsSet = claimsBuilder
                    .issuer("test-app")
                    .issueTime(Date.from(Instant.now()))
                    .expirationTime(Date.from(Instant.now().plus(Duration.ofMinutes(5))))
                    .jwtID(UUID.randomUUID().toString())
                    .build();

            JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.RS512)
                    .type(JOSEObjectType.JWT)
                    .keyID("testKeyId")
                    .build();

            SignedJWT signedJWT = new SignedJWT(header, claimsSet);

            JWSSigner signer = new RSASSASigner(keyStoreProvider.getPrivateKey());
            signedJWT.sign(signer);

            return signedJWT.serialize();

        } catch (Exception e) {
            throw new IllegalStateException("Failed to generate JWT", e);
        }
    }
}



