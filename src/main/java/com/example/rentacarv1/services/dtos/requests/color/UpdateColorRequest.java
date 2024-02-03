package com.example.rentacarv1.services.dtos.requests.color;

import com.example.rentacarv1.services.constants.color.ColorMessages;
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
public class UpdateColorRequest {
    @NotNull(message = ColorMessages.COLOR_NOT_NULL)
    @Positive(message = ColorMessages.POSITIVE_NUMBER)
    private int id;
    @NotBlank(message = ColorMessages.COLOR_NOT_BLANK)
    @Size(min = 3,max = 20,message =ColorMessages.COLOR_SHOULD_BE_BETWEEN_2_AND_20_CHARACTERS)
    private String name;
}
