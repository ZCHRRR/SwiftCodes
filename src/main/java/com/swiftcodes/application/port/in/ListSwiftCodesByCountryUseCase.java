package com.swiftcodes.application.port.in;

import com.swiftcodes.domain.model.SwiftCode;

import java.util.List;

public interface ListSwiftCodesByCountryUseCase {

    List<SwiftCode> listByCountry(String countryIso2);
}