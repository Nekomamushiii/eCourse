package mn.mlc.elearining.validation.passwordMatch;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

public class PasswordMatcher implements ConstraintValidator<PasswordMatch,Object> {
    private String password;
    private String confirmPassword;
    private String message;
    @Override
    public void initialize(PasswordMatch match) {
       this.password=match.password();
       this.confirmPassword=match.confirmPassword();
       this.message= match.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(value);
        Object passwordValue = wrapper.getPropertyValue(this.password);
        Object confirmPasswordValue = wrapper.getPropertyValue(this.confirmPassword);
        if(passwordValue != null && passwordValue.equals(confirmPasswordValue))
            return true;
        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode(confirmPassword)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
