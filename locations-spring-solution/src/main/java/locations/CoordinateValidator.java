package locations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CoordinateValidator implements ConstraintValidator<Coordinate, Double> {

    private Type type;

    @Override
    public boolean isValid(Double aDouble, ConstraintValidatorContext constraintValidatorContext) {
        if (type == Type.LAT) {
            return Math.abs(aDouble) < 90;
        }
        return Math.abs(aDouble) < 180;
    }

    @Override
    public void initialize(Coordinate constraintAnnotation) {
        type = constraintAnnotation.type();
    }
}
