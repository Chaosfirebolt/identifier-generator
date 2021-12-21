package com.github.chaosfirebolt.generator.part;

import com.github.chaosfirebolt.generator.token.part.UpperAlphabeticTokenPart;
import org.junit.jupiter.api.function.Executable;

/**
 * Created by ChaosFire on 19-Dec-21
 */
public class UpperAlphabeticTokenPartConstructorTests extends TokenPartConstructorTests {

    @Override
    protected Executable intConstructor_LengthIsNegative() {
        return () -> new UpperAlphabeticTokenPart(-4);
    }

    @Override
    protected Executable intConstructor_LengthIsZero() {
        return () -> new UpperAlphabeticTokenPart(0);
    }

    @Override
    protected Executable intConstructor_LengthIsPositive() {
        return () -> new UpperAlphabeticTokenPart(4);
    }

    @Override
    protected Executable intIntConstructor_MinLengthIsNegative() {
        return () -> new UpperAlphabeticTokenPart(4, -9);
    }

    @Override
    protected Executable intIntConstructor_MinLengthIsZero() {
        return () -> new UpperAlphabeticTokenPart(4, 0);
    }

    @Override
    protected Executable intIntConstructor_MinLengthIsPositive() {
        return () -> new UpperAlphabeticTokenPart(4, 2);
    }

    @Override
    protected Executable intIntConstructor_LengthIsLessThanMinLength() {
        return () -> new UpperAlphabeticTokenPart(8, 11);
    }
}