package org.training360.musicstore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInstrumentCommand {

    @NotBlank(message = "Name Cannot be blank")
    private String brand;

    private InstrumentType instrumentType;

    @PositiveOrZero(message = "price must be positive")
    private long price;
}
