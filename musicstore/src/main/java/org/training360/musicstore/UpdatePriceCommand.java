package org.training360.musicstore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePriceCommand {

    @Min(value = 0, message = "price must be over 0")
    private long price;

}
