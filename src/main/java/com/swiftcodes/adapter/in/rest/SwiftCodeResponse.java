package com.swiftcodes.adapter.in.rest;

import com.swiftcodes.domain.model.SwiftCode;

public record SwiftCodeResponse(
        String swiftCode,
        String codeType,
        String name,
        String address,
        String countryIso2,
        String countryName,
        String townName,
        String timeZone
) {

    public static SwiftCodeResponse from(SwiftCode domain) {
        return new SwiftCodeResponse(
                domain.swiftCode(),
                domain.codeType(),
                domain.name(),
                domain.address(),
                domain.countryIso2(),
                domain.countryName(),
                domain.townName(),
                domain.timeZone()
        );
    }
}