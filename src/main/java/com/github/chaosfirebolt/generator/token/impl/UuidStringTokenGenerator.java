package com.github.chaosfirebolt.generator.token.impl;

import com.github.chaosfirebolt.generator.token.StringTokenGenerator;

import java.util.UUID;

/**
 * Implementation generating tokens as random uuid strings.
 * <br/>
 * Created by ChaosFire on 12/5/2021
 */
public class UuidStringTokenGenerator extends StringTokenGenerator {

    public UuidStringTokenGenerator() {
        super(null, null, null);
    }

    @Override
    public String generate() {
        return generateRandomUuidString();
    }

    @Override
    public String generate(int tokenLength) {
        return generateRandomUuidString();
    }

    private static String generateRandomUuidString() {
        return UUID.randomUUID().toString();
    }
}