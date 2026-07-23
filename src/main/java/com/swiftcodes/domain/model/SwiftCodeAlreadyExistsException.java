package com.swiftcodes.domain.model;

public class SwiftCodeAlreadyExistsException extends RuntimeException {

    public SwiftCodeAlreadyExistsException(String swiftCode) {
        super("SWIFT code already exists: " + swiftCode);
    }
}