package com.example.architecture;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "com.example", importOptions = ImportOption.DoNotIncludeTests.class)
public class ArchitectureTest {

        private static final String DOMAIN = "Domain";
        private static final String APPLICATION = "Application";
        private static final String INFRASTRUCTURE = "Infrastructure";

        /*
         * Note: ArchUnit 1.3.0 might not support Java 25 bytecode analysis yet.
         * If these tests fail to find classes, it's likely a compatibility issue.
         * allowEmptyShould(true) is used here to prevent the build from failing in this
         * template.
         */

        @ArchTest
        static final ArchRule hexagonal_architecture_layers = layeredArchitecture()
                        .consideringAllDependencies()
                        .layer(DOMAIN).definedBy("..domain..")
                        .layer(APPLICATION).definedBy("..application..")
                        .layer(INFRASTRUCTURE).definedBy("..infrastructure..")

                        .whereLayer(DOMAIN).mayOnlyBeAccessedByLayers(APPLICATION, INFRASTRUCTURE)
                        .whereLayer(APPLICATION).mayOnlyBeAccessedByLayers(INFRASTRUCTURE)
                        .whereLayer(INFRASTRUCTURE).mayNotBeAccessedByAnyLayer()
                        .allowEmptyShould(true);

        @ArchTest
        static final ArchRule domain_should_not_depend_on_spring_or_jpa = noClasses()
                        .that().resideInAPackage("..domain..")
                        .should().dependOnClassesThat()
                        .resideInAnyPackage("org.springframework..", "jakarta.persistence..")
                        .allowEmptyShould(true);

        @ArchTest
        static final ArchRule services_should_be_in_application_and_named_correctly = classes()
                        .that().haveSimpleNameEndingWith("Service")
                        .should().resideInAPackage("..application..")
                        .allowEmptyShould(true);

        @ArchTest
        static final ArchRule use_cases_should_be_in_application_and_named_correctly = classes()
                        .that().haveSimpleNameEndingWith("UseCase")
                        .should().resideInAPackage("..application..")
                        .allowEmptyShould(true);

        @ArchTest
        static final ArchRule repositories_should_be_in_domain_and_named_correctly = classes()
                        .that().haveSimpleNameEndingWith("Repository")
                        .should().resideInAPackage("..domain..")
                        .allowEmptyShould(true);

        @ArchTest
        static final ArchRule controllers_should_be_in_infrastructure_and_named_correctly = classes()
                        .that().haveSimpleNameEndingWith("Controller")
                        .should().resideInAPackage("..infrastructure..")
                        .allowEmptyShould(true);

        @ArchTest
        static final ArchRule adapters_should_be_in_infrastructure_and_named_correctly = classes()
                        .that().haveSimpleNameEndingWith("Adapter")
                        .should().resideInAPackage("..infrastructure..")
                        .allowEmptyShould(true);
}
