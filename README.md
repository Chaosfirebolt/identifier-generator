# Identifier Generator
Library for identifier generation.
Provides abstraction for identifier generation - main interface defining functionality is [IdentifierGenerator](src/main/java/com/github/chaosfirebolt/generator/identifier/IdentifierGenerator.java), and implementations for string based identifiers.
Most combinations of alphabetic, numeric and special characters are covered by provided implementations.

# Latest version
Current latest version is 1.0.0
<br/>
Maven dependency
```
<dependency>
    <groupId>com.github.chaosfirebolt.generator</groupId>
    <artifactId>identifier-generator</artifactId>
    <version>1.0.0</version>
</dependency>
```

# Examples

Print alphanumeric identifier
```
IdentifierGenerator<String> generator = new AlphaNumericIdentifierGenerator(10, 10, 3);
String token = generator.generate();
System.out.println(token);
//prints - QHx0qPCWnFjdxd5MFseS8Fl for example.
```

Print lower alphabetic identifier
```
IdentifierGenerator<String> generator = new LowerAlphabeticIdentifierGenerator(19);
String identifier = generator.generate();
System.out.println(identifier);
//prints - jvqacptjtzocgmhqrtq for example.
```

Print uuid identifier
```
IdentifierGenerator<String> generator = new UuidStringIdentifierGenerator();
String identifier = generator.generate();
System.out.println(identifier);
//prints - d3f4d5c5-050e-4e5b-a44e-014deb694bf7 for example.
```

Print identifiers comprising numeric and special characters, which are previously generated
```
Set<String> existing = new HashSet<>();
Part numericPart = new NumericPart(19);
Part specialPart = new SpecialCharacterPart(5);
GeneratorRule generatorRule = new BaseGeneratorRule(Arrays.asList(numericPart, specialPart));

int minTotalLength = 10;
Predicate<GeneratorRule> ruleValidityCondition = rule -> rule.getLength() >= minTotalLength;
ErrorMessageCreator errorMessageCreator = rule -> "Minimum identifier length is " + minTotalLength;
RuleValidator ruleValidator = new BaseRuleValidator(ruleValidityCondition, errorMessageCreator);

IdentifierGenerator<String> generator = new StringIdentifierGenerator(generatorRule, Collections.singletonList(ruleValidator));
for (int i = 0; i < 10_000; i++) {
    String identifier = generator.generate(existing::add);
    System.out.println("Identifier - " + identifier);
    //for example - 28999`957211294`6638|2^"
}
```

Invalid - either example will throw IllegalArgumentException
```
IdentifierGenerator<String> generator1 = new LowerAlphabeticIdentifierGenerator(0);
IdentifierGenerator<String> generator2 = new LowerAlphabeticIdentifierGenerator(-3);
```

Invalid - will throw InvalidGeneratorRuleException
```
int minLength = 11;
RuleValidator validator = new BaseRuleValidator(rule -> rule.getLength() >= minLength, rule -> "identifier length can't be less than " + minLength);
IdentifierGenerator<String> generator = new NumericIdentifierGenerator(9, Collections.singletonList(validator));
```

Throwing exception if unable to generate unique identifier after too many attempts
```
Set<String> existing = new HashSet<>();
//length is short, to make sure all combinations are created quickly
NumericIdentifierGenerator generator = new NumericIdentifierGenerator(2);
//setting maximum attempts to generate unique id, default will attempt forever
generator.setMaximumAttempts(10);
for (int i = 0; i < 100; i++) {
    String identifier = generator.generate(existing::add);
    System.out.println("Identifier - " + identifier);
    //will throw TooManyAttemptsException at some point during this loop
}
```

# Licence
This project is licensed under the Apache License 2.0 License - see the [LICENSE](LICENSE.txt) file for details.