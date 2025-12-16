package Dto.Request;

import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
public class PedidoRequestDTO {
    @NotNull(message = "El ID del usuario no puede ser nulo")
    private Long userId;
}
