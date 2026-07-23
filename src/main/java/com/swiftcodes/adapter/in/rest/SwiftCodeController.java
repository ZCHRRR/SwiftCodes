package com.swiftcodes.adapter.in.rest;

import com.swiftcodes.application.port.in.AddSwiftCodeUseCase;
import com.swiftcodes.application.port.in.DeleteSwiftCodeUseCase;
import com.swiftcodes.application.port.in.GetSwiftCodeUseCase;
import com.swiftcodes.application.port.in.ListSwiftCodesByCountryUseCase;
import com.swiftcodes.domain.model.SwiftCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/swift-codes")
@Tag(name = "SWIFT codes", description = "Operacje na rejestrze kodów SWIFT")
public class SwiftCodeController {

    private final GetSwiftCodeUseCase getSwiftCodeUseCase;
    private final ListSwiftCodesByCountryUseCase listSwiftCodesByCountryUseCase;
    private final AddSwiftCodeUseCase addSwiftCodeUseCase;
    private final DeleteSwiftCodeUseCase deleteSwiftCodeUseCase;

    public SwiftCodeController(GetSwiftCodeUseCase getSwiftCodeUseCase,
                               ListSwiftCodesByCountryUseCase listSwiftCodesByCountryUseCase,
                               AddSwiftCodeUseCase addSwiftCodeUseCase,
                               DeleteSwiftCodeUseCase deleteSwiftCodeUseCase) {
        this.getSwiftCodeUseCase = getSwiftCodeUseCase;
        this.listSwiftCodesByCountryUseCase = listSwiftCodesByCountryUseCase;
        this.addSwiftCodeUseCase = addSwiftCodeUseCase;
        this.deleteSwiftCodeUseCase = deleteSwiftCodeUseCase;
    }

    @Operation(
            summary = "Lista banków z kraju",
            description = "Zwraca wszystkie banki o podanym dwuliterowym kodzie kraju ISO2. "
                    + "Dla kraju bez rekordów zwraca pustą listę, nie 404.")
    @ApiResponse(responseCode = "200", description = "Lista (możliwie pusta)")
    @GetMapping
    public List<SwiftCodeResponse> listByCountry(
            @RequestParam
            @Parameter(description = "Dwuliterowy kod kraju ISO 3166-1 alpha-2", example = "PL")
            String countryIso2) {
        List<SwiftCode> found = listSwiftCodesByCountryUseCase.listByCountry(countryIso2);
        List<SwiftCodeResponse> result = new ArrayList<>();
        for (SwiftCode swiftCode : found) {
            result.add(SwiftCodeResponse.from(swiftCode));
        }
        return result;
    }

    @Operation(
            summary = "Pobierz bank po kodzie SWIFT",
            description = "Zwraca pojedynczy rekord banku identyfikowany pełnym kodem SWIFT.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Bank znaleziony"),
            @ApiResponse(responseCode = "404", description = "Brak banku o podanym kodzie SWIFT",
                    content = @Content)
    })
    @GetMapping("/{swiftCode}")
    public ResponseEntity<SwiftCodeResponse> getBySwiftCode(
            @PathVariable
            @Parameter(description = "Pełny kod SWIFT, 8 lub 11 znaków", example = "BREXPLPWXXX")
            String swiftCode) {
        Optional<SwiftCode> found = getSwiftCodeUseCase.getBySwiftCode(swiftCode);
        if (found.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(SwiftCodeResponse.from(found.get()));
    }

    @Operation(
            summary = "Dodaj bank",
            description = "Tworzy nowy rekord. Kod SWIFT jest kluczem — próba dodania "
                    + "istniejącego kończy się konfliktem.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Utworzono"),
            @ApiResponse(responseCode = "400", description = "Ciało żądania nie przeszło walidacji",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Bank o tym kodzie SWIFT już istnieje",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping
    public ResponseEntity<SwiftCodeResponse> add(@Valid @RequestBody CreateSwiftCodeRequest request) {
        SwiftCode saved = addSwiftCodeUseCase.add(request.toDomain());
        URI location = URI.create("/swift-codes/" + saved.swiftCode());
        return ResponseEntity.created(location).body(SwiftCodeResponse.from(saved));
    }

    @Operation(summary = "Usuń bank po kodzie SWIFT")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Usunięto"),
            @ApiResponse(responseCode = "404", description = "Brak banku o podanym kodzie SWIFT",
                    content = @Content)
    })
    @DeleteMapping("/{swiftCode}")
    public ResponseEntity<Void> delete(
            @PathVariable
            @Parameter(description = "Pełny kod SWIFT, 8 lub 11 znaków", example = "BREXPLPWXXX")
            String swiftCode) {
        if (deleteSwiftCodeUseCase.delete(swiftCode)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}