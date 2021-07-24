package mathematician;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = PrimeValidator.class)
public @interface Prime {

    String message() default "Invalid favourite prime! Value must be between 1-100";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
