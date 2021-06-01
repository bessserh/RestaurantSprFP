package restaurantSF.finalproject.errorValid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ValidationException extends Exception {

    private String message;

    public String getMessage() {
        return message;
    }
}