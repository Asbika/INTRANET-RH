package com.giantLink.RH.utilities.validators;

import com.giantLink.RH.models.request.RequestHolidayRequest;
import com.giantLink.RH.utilities.validators.FinishDateAfterStartDate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Date;

public class FinishDateAfterStartDateValidator implements ConstraintValidator<FinishDateAfterStartDate, RequestHolidayRequest>
{
    @Override
    public void initialize(FinishDateAfterStartDate constraintAnnotation) {}

    @Override
    public boolean isValid(RequestHolidayRequest request, ConstraintValidatorContext context)
    {
        Date startDate = request.getStartDate();
        Date finishDate = request.getFinishDate();

        return finishDate.after(startDate);
    }
}

