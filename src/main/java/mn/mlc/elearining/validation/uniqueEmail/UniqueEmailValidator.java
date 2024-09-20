package mn.mlc.elearining.validation.uniqueEmail;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import mn.mlc.elearining.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueEmailValidator implements ConstraintValidator<ValidateUniqueEmail,String> {
    private final UserRepository userRepository;
    @Autowired
    public UniqueEmailValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return this.userRepository.findByEmail(email).isEmpty();
    }
}
