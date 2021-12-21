package com.github.chaosfirebolt.generator.part;

import com.github.chaosfirebolt.generator.token.part.SpecialCharacterTokenPart;
import org.junit.jupiter.api.function.Executable;

/**
 * Created by ChaosFire on 19-Dec-21
 */
public class SpecialCharacterTokenPartConstructorTests extends TokenPartConstructorTests {

    @Override
    protected Executable intConstructor_LengthIsNegative() {
        return () -> new SpecialCharacterTokenPart(-4);
    }

    @Override
    protected Executable intConstructor_LengthIsZero() {
        return () -> new SpecialCharacterTokenPart(0);
    }

    @Override
    protected Executable intConstructor_LengthIsPositive() {
        return () -> new SpecialCharacterTokenPart(4);
    }

    @Override
    protected Executable intIntConstructor_MinLengthIsNegative() {
        return () -> new SpecialCharacterTokenPart(4, -9);
    }

    @Override
    protected Executable intIntConstructor_MinLengthIsZero() {
        return () -> new SpecialCharacterTokenPart(4, 0);
    }

    @Override
    protected Executable intIntConstructor_MinLengthIsPositive() {
        return () -> new SpecialCharacterTokenPart(4, 2);
    }

    @Override
    protected Executable intIntConstructor_LengthIsLessThanMinLength() {
        return () -> new SpecialCharacterTokenPart(8, 11);
    }
}