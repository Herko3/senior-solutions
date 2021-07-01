package org.training360.musicstore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInstrumentCommand {

    @NotBlank(message = "Name Cannot be blank")
    private String brand;

    private InstrumentType instrumentType;

    @Min(value = 0, message = "price must be over 0")
    private long price;
}
