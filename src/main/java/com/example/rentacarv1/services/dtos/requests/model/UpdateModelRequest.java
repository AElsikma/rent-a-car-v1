package com.example.rentacarv1.services.dtos.requests.model;

import com.example.rentacarv1.services.constants.model.ModelMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateModelRequest {
    @NotNull(message =ModelMessages.MODEL_NOT_NULL)
    @Positive(message = ModelMessages.POSITIVE_NUMBER)
    private int id;

    @NotBlank(message = ModelMessages.MODEL_NOT_BLANK)
    @Size(min = 2, message = ModelMessages.NAME_SHOULD_BE_BETWEEN_2_AND_20_CHARACTERS)
    private String name;

    @NotNull(message = ModelMessages.MODEL_NOT_NULL)
    @Positive(message =ModelMessages.POSITIVE_NUMBER)
    private int brandId;
}
