package ma.mouloudia.club.oujda;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("ma.mouloudia.club.oujda");

        noClasses()
            .that()
                .resideInAnyPackage("ma.mouloudia.club.oujda.service..")
            .or()
                .resideInAnyPackage("ma.mouloudia.club.oujda.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..ma.mouloudia.club.oujda.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
