package com.swiftcodes.application.port.out;

import com.swiftcodes.domain.model.SwiftCode;

import java.util.List;
import java.util.Optional;

public interface SwiftCodeRepository {

    Optional<SwiftCode> findBySwiftCode(String swiftCode);

    List<SwiftCode> findByCountryIso2(String countryIso2);

    SwiftCode save(SwiftCode swiftCode);

    void deleteBySwiftCode(String swiftCode);

    long count();
}