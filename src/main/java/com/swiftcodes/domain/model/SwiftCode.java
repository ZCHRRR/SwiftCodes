package com.swiftcodes.domain.model;

public record SwiftCode(
        String swiftCode,
        String codeType,
        String name,
        String address,
        String countryIso2,
        String countryName,
        String townName,
        String timeZone
) {

    public SwiftCode {
        if (swiftCode == null || swiftCode.isBlank()) {
            throw new IllegalArgumentException("swiftCode nie może być pusty");
        }
        if (countryIso2 == null || countryIso2.isBlank()) {
            throw new IllegalArgumentException("countryIso2 nie może być pusty");
        }
    }
}