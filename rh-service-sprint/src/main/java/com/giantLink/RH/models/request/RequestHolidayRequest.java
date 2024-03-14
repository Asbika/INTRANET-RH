package com.giantLink.RH.models.request;

import com.giantLink.RH.utilities.validators.FinishDateAfterStartDate;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;


import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
@FinishDateAfterStartDate
public class RequestHolidayRequest
{
    @Min(value = 1, message = "Number of days must be at least 1.")
    private int numberOfDays;


    @NotNull(message = "Start date cannot be empty.")
    @Future(message = "Start date must be in the future.")
    private Date startDate;

    @NotNull(message = "Finish date cannot be empty.")
    @Future(message = "Finish date must be in the future.")
    private Date finishDate;

/*    @NotNull(message = "Return date cannot be empty.")
    @Future(message = "Return date must be in the future.")
    private Date returnDate;*/

    @NotNull(message = "Employee ID cannot be empty.")
    private Long employee_id;
}
