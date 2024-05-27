# Identifier Generator
Library for identifier generation.
Provides abstraction for identifier generation - main interfaces defining functionality are [IdentifierGenerator](src/main/java/com/github/chaosfirebolt/generator/identifier/api/IdentifierGenerator.java) and [TargetLengthIdentifierGenerator](src/main/java/com/github/chaosfirebolt/generator/identifier/api/TargetLengthIdentifierGenerator.java), and implementations for string based identifiers.
Most combinations of alphabetic, numeric and special characters are covered by provided [builders](src/main/java/com/github/chaosfirebolt/generator/identifier/api/string/builders/StringGeneratorBuilders.java).
Also supports sequence based generators with [SequentialIdentifierGenerator](src/main/java/com/github/chaosfirebolt/generator/identifier/api/sequential/SequentialIdentifierGenerator.java).

Versions before `2.0.0` require java 8. Since `2.0.0` required java version is 17.

# Latest version
Current latest version is 2.2.0
<br/>
Maven dependency
```
<dependency>
    <groupId>com.github.chaosfirebolt.generator</groupId>
    <artifactId>identifier-generator</artifactId>
    <version>2.2.0</version>
</dependency>
```
[All artefacts in maven central](https://mvnrepository.com/artifact/com.github.chaosfirebolt.generator/identifier-generator)

[Javadoc](https://javadoc.io/doc/com.github.chaosfirebolt.generator/identifier-generator/latest/identifier.generator/module-summary.html)

# Examples

Print alphanumeric identifier
```
IdentifierGenerator<String> generator = StringGeneratorBuilders.alphaNumericIdentifierGeneratorBuilder()
                .setLowerCaseLength(10)
                .setUpperCaseLength(10)
                .setNumericLength(3)
                .build();
String token = generator.generate();
System.out.println(token);
//prints - yXJpAwjD3zZbi7ePQ3vSXMf for example.
```

Print lower alphabetic identifier
```
IdentifierGenerator<String> generator = StringGeneratorBuilders.lowerAlphabeticIdentifierGeneratorBuilder()
                .setLowerCaseLength(19)
                .build();
String identifier = generator.generate();
System.out.println(identifier);
//prints - bmqnsrcuzfkpyggjgyv for example.
```

Print uuid identifier
```
IdentifierGenerator<String> generator = new RandomUuidStringIdentifierGenerator();
String identifier = generator.generate();
System.out.println(identifier);
//prints - d3f4d5c5-050e-4e5b-a44e-014deb694bf7 for example.
```

Print identifiers comprising numeric and special characters, with uniqueness condition
```
Set<String> existing = new HashSet<>();
Part numericPart = new NumericPart(19);
Part specialPart = new SpecialCharacterPart(5);
GeneratorRule generatorRule = new BaseGeneratorRule(Arrays.asList(numericPart, specialPart));

int minTotalLength = 10;
Predicate<GeneratorRule> ruleValidityCondition = rule -> rule.getLength() >= minTotalLength;
ErrorMessageCreator errorMessageCreator = rule -> "Minimum identifier length is " + minTotalLength;
RuleValidator ruleValidator = new BaseRuleValidator(ruleValidityCondition, errorMessageCreator);

IdentifierGenerator<String> generator = StringGeneratorBuilders.stringIdentifierGeneratorBuilder()
        .setGeneratorRule(generatorRule)
        .setRandomGenerator(RandomGenerator.of("Xoroshiro128PlusPlus"))
        .addRuleValidator(ruleValidator)
        .build();
for (int i = 0; i < 10_000; i++) {
    String identifier = generator.generate(existing::add);
    System.out.println("Identifier - " + identifier);
    //for example - 7+!602!14822943^7,405917
}
```

Invalid - either example will throw IllegalArgumentException
```
IdentifierGenerator<String> generator1 = StringGeneratorBuilders.lowerAlphabeticIdentifierGeneratorBuilder()
                .setLowerCaseLength(0)
                .build();
IdentifierGenerator<String> generator2 = StringGeneratorBuilders.lowerAlphabeticIdentifierGeneratorBuilder()
                .setLowerCaseLength(-3)
                .build();
```

Invalid - will throw InvalidGeneratorRuleException
```
int minLength = 11;
RuleValidator validator = new BaseRuleValidator(rule -> rule.getLength() >= minLength, rule -> "identifier length can't be less than " + minLength);
IdentifierGenerator<String> generator = StringGeneratorBuilders.numericIdentifierGeneratorBuilder()
        .setNumericLength(9)
        .setRuleValidators(List.of(validator))
        .build();
```

Throwing exception if unable to generate unique identifier after too many attempts
```
Set<String> existing = new HashSet<>();
//length is short, to make sure all combinations are created quickly
StringIdentifierGenerator generator = StringGeneratorBuilders.numericIdentifierGeneratorBuilder()
        .setNumericLength(2)
        .build();
//setting maximum attempts to generate unique id, default will attempt forever
generator.setMaximumAttempts(10);
for (int i = 0; i < 100; i++) {
  String identifier = generator.generate(existing::add);
  System.out.println("Identifier - " + identifier);
  //will throw TooManyAttemptsException at some point during this loop
}
```

Sequence identifier generator with **all** options. [DateDecoration](src/test/java/com/github/chaosfirebolt/generator/identifier/sequential/DateDecoration.java)
```java
public static void main(String[] args) {
  SequentialIdentifierGenerator<Integer, String> generator = SequentialIdentifierGenerator.<Integer, String>fluidTypeBuilder()
          .setSequence(SequenceFactories.infinite(1, num -> num + 1))
          .setMapper(num -> String.format("%05d", num))//pad left with zeroes
          .setDecoration(new DateDecoration(Clock.systemUTC()))//decoration prepending the date and resetting sequence for the next day
          .setExceptionFactory(() -> new MyCustomException("No more identifiers"))//throw some custom exception when the generator cannot generate more identifiers
          .build();
  for (int i = 0; i < 5; i++) {
    System.out.println(generator.generate());
  }
  //At the time of writing prints
  //2023122400001
  //2023122400002
  //2023122400003
  //2023122400004
  //2023122400005
}
```

Sequence identifier generator with **minimalistic** setup.
```java
public static void main(String[] args) {
  SequentialIdentifierGenerator<Integer, Integer> generator = SequentialIdentifierGenerator.<Integer>constantTypeBuilder()
          .setSequence(SequenceFactories.infinite(1, num -> num + 1))
          .build();
  for (int i = 0; i < 5; i++) {
    System.out.println(generator.generate());
  }
}
```

Sequence identifier generator backed by a collection.
```java
public static void main(String[] args) {
  SequentialIdentifierGenerator<Integer, Integer> generator = SequentialIdentifierGenerator.<Integer>constantTypeBuilder()
          .setSequence(SequenceFactories.iterable(List.of(1, 2, 3, 4, 5, 6, 7)))
          .build();
  for (int i = 0; i < 5; i++) {
    System.out.println(generator.generate());
  }
}
```

# Licence
This project is licensed under the Apache License 2.0 License - see the [LICENSE](LICENSE.txt) file for details.
