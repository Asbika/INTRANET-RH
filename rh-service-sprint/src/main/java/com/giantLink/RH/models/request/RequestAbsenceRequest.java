package com.giantLink.RH.models.request;

import java.util.Date; 
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
@Data
public class RequestAbsenceRequest {

	    @NotNull(message = "La date d'absence ne peut pas être nulle.")
	    @JsonFormat(pattern="dd-MM-yyyy")
	    private Date absenceDate;

	    @NotNull(message = "L'heure de sortie ne peut pas être nulle.")
	    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm")
	    private Date exitTime;

	    @NotNull(message = "L'heure d'entrée ne peut pas être nulle.")
	    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm")
	    private Date entryTime;

	    private boolean sickness;

	    @NotBlank(message = "La raison ne peut pas être vide.")
	    private String reason;

	    @NotNull(message = "L'ID de l'employé ne peut pas être nul.")
	    @Positive(message = "L'ID de l'employé doit être supérieur à zéro.")
	    private Long idEmployee;

	    private boolean justification;
}
