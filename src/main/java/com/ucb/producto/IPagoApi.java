package com.ucb.producto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;

import com.ucb.producto.dto.LoginDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

public interface IPagoApi {
    @Tag(name = "Pago con tarjeta credito", description = "Obtain tarjeta de credito ")
    @Operation(summary = "tarjeta de credito", description = "Loren ipsum")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200", description = "La solicitud ha tenido éxito."
            ),
            @ApiResponse(
                responseCode = "400", description = "El servidor no pudo interpretar la solicitud dada una sintaxis inválida."
            ),
            @ApiResponse(
                responseCode = "404", description = "El servidor no pudo encontrar el contenido solicitado. "
            ),
            @ApiResponse(
                responseCode = "500", description = "${api.responseCodes.internalServer.description}",
                 content = {
                    @Content(mediaType = "application/json",
                             schema = @Schema(implementation = ErrorResponse.class))
                 }

            )
        }
    )

    public String index();

    @Tag(name = "PAgo", description = "Paymentt")
    public ResponseEntity<PaymentResponse> obtain();

    @Tag(name = "Pago", description = "Paga con targeta de credito")
    public ResponseEntity<PaymentResponse> pago(PaymentResponse log);
}
