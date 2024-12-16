package com.github.rusichpt;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        Account myAccount = new Account()
                .setBalance(2000.00)
                .setAccountHolder("John Snow");

        LOG.info("Hello world {}", myAccount);
    }
}