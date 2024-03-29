/**
 * Module configuration.
 */
module identifier.generator {

  requires org.apiguardian.api;
  requires org.slf4j;

  exports com.github.chaosfirebolt.generator.identifier.api;
  exports com.github.chaosfirebolt.generator.identifier.api.exception;
  exports com.github.chaosfirebolt.generator.identifier.api.string;
  exports com.github.chaosfirebolt.generator.identifier.api.string.builders;
  exports com.github.chaosfirebolt.generator.identifier.api.string.part;
  exports com.github.chaosfirebolt.generator.identifier.api.string.rule;
  exports com.github.chaosfirebolt.generator.identifier.api.string.validation;

  exports com.github.chaosfirebolt.generator.identifier.api.uuid;

  exports com.github.chaosfirebolt.generator.identifier.api.sequential;
  exports com.github.chaosfirebolt.generator.identifier.api.sequential.builders;
  exports com.github.chaosfirebolt.generator.identifier.api.sequential.calculation;
  exports com.github.chaosfirebolt.generator.identifier.api.sequential.sequence;
  exports com.github.chaosfirebolt.generator.identifier.api.sequential.export;
}
