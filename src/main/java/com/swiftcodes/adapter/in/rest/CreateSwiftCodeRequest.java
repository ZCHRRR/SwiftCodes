package com.swiftcodes.adapter.in.rest;

import com.swiftcodes.domain.model.SwiftCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateSwiftCodeRequest(

        @NotBlank
        @Size(min = 8, max = 11)
        @Pattern(regexp = "[A-Z0-9]+")
        String swiftCode,

        @NotBlank
        String codeType,

        @NotBlank
        String name,

        String address,

        @NotBlank
        @Pattern(regexp = "[A-Z]{2}")
        String countryIso2,

        @NotBlank
        String countryName,

        String townName,

        String timeZone
) {

    public SwiftCode toDomain() {
        return new SwiftCode(
                swiftCode,
                codeType,
                name,
                address,
                countryIso2,
                countryName,
                townName,
                timeZone
        );
    }
}