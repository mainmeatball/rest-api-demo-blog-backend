package helloblog.controller.validator;

import org.springframework.stereotype.Service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class RolesValidator implements ConstraintValidator<ValidRoles, Set<String>> {

    private final Set<String> validRoles = new HashSet<>(Arrays.asList("ROLE_ADMIN", "ROLE_USER", "ROLE_MODERATOR"));

    @Override
    public void initialize(ValidRoles constraintAnnotation) {
    }

    @Override
    public boolean isValid(Set<String> roles, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println(roles);
        return  roles != null
                && !roles.isEmpty()
                && validRoles.containsAll(roles);
    }
}
