package com.ucb.producto;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion.VersionFlag;
import com.networknt.schema.ValidationMessage;
import com.ucb.producto.dto.LoginDto;

import io.sentry.Sentry;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

public class LoginController implements ILoginApi {
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
    public ResponseEntity<AuthResponse> obtain() {
        
        var response = new AuthResponse("token", 154851);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity login(@RequestBody LoginDto login) {

        ObjectMapper mapper = new ObjectMapper();
        String json;
        try {
            json = mapper.writeValueAsString(login);
            JsonSchemaFactory factory = JsonSchemaFactory.getInstance(VersionFlag.V7);
            JsonSchema jsonSchema = factory.getSchema(LoginController.class.getClassLoader().getResourceAsStream("schemas/login.json"));
            JsonNode jsonNode = mapper.readTree(json);
            Set<ValidationMessage> errors = jsonSchema.validate(jsonNode); 

            String errorsCombined = "";
            for( ValidationMessage error: errors) {
                errorsCombined += error.toString() +  "\n";
            }

            if(errors.size() > 0) {
                return ResponseEntity.badRequest().body("Please fix your JSON!,\n"+errorsCombined);
            }
            return ResponseEntity.ok(login);

        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return ResponseEntity.ok(login);
        }
    }
}
