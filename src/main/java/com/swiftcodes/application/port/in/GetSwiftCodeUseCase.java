package com.swiftcodes.application.port.in;

import com.swiftcodes.domain.model.SwiftCode;

import java.util.Optional;

public interface GetSwiftCodeUseCase {

    Optional<SwiftCode> getBySwiftCode(String swiftCode);
}