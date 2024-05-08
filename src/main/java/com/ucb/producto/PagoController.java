package com.ucb.producto;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion.VersionFlag;
import com.networknt.schema.ValidationMessage;

import io.sentry.Sentry;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

public class PagoController implements IPagoApi{
     @GetMapping("/")
    public String index() {
        try {
            throw new Exception("This is a test.");
          } catch (Exception e) {
            Sentry.captureException(e);
          }
        return "Greetings from Spring boot";
    }

    @GetMapping( value = "/products/{id}", produces = "application/json")
    public ResponseEntity<PaymentResponse> obtain() {
        
        var response = new PaymentResponse();
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity pago(@RequestBody PaymentResponse pago) {

        ObjectMapper mapper = new ObjectMapper();
        String json;
        try {
            json = mapper.writeValueAsString(pago);
            JsonSchemaFactory factory = JsonSchemaFactory.getInstance(VersionFlag.V7);
            JsonSchema jsonSchema = factory.getSchema(LoginController.class.getClassLoader().getResourceAsStream("schemas/pago.json"));
            JsonNode jsonNode = mapper.readTree(json);
            Set<ValidationMessage> errors = jsonSchema.validate(jsonNode); 

            String errorsCombined = "";
            for( ValidationMessage error: errors) {
                errorsCombined += error.toString() +  "\n";
            }

            if(errors.size() > 0) {
                return ResponseEntity.badRequest().body("Please fix your JSON!,\n"+errorsCombined);
            }
            return ResponseEntity.ok(pago);

        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return ResponseEntity.ok(pago);
        }
    }
}
