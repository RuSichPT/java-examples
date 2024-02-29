package com.github.rusichpt.configproperties;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

@Data
@ConfigurationProperties(prefix = "mail")
@Validated
public class ConfigProperties {

    /**
     * host name
     */
    @NotBlank
    private String hostName;

    /**
     * port
     */
    @Min(1025)
    @Max(65536)
    private int port;

    /**
     * from
     */
    @Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$")
    private String from;
    /**
     * List defaultRecipients
     */
    private List<String> defaultRecipients;
    /**
     * Map additionalHeaders
     */
    private Map<String, String> additionalHeaders;
    /**
     * Object Credentials
     */
    private Credentials credentials;
}
