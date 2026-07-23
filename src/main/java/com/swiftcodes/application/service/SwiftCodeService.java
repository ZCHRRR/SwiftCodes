package com.swiftcodes.application.service;

import com.swiftcodes.application.port.in.AddSwiftCodeUseCase;
import com.swiftcodes.application.port.in.GetSwiftCodeUseCase;
import com.swiftcodes.application.port.in.ListSwiftCodesByCountryUseCase;
import com.swiftcodes.application.port.in.DeleteSwiftCodeUseCase;
import com.swiftcodes.application.port.out.SwiftCodeRepository;
import com.swiftcodes.domain.model.SwiftCode;
import com.swiftcodes.domain.model.SwiftCodeAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SwiftCodeService implements GetSwiftCodeUseCase, ListSwiftCodesByCountryUseCase, AddSwiftCodeUseCase, DeleteSwiftCodeUseCase {

    private final SwiftCodeRepository repository;

    public SwiftCodeService(SwiftCodeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<SwiftCode> getBySwiftCode(String swiftCode) {
        return repository.findBySwiftCode(swiftCode);
    }

    @Override
    public List<SwiftCode> listByCountry(String countryIso2) {
        return repository.findByCountryIso2(countryIso2);
    }

    @Override
    public SwiftCode add(SwiftCode swiftCode) {
        if (repository.findBySwiftCode(swiftCode.swiftCode()).isPresent()) {
            throw new SwiftCodeAlreadyExistsException(swiftCode.swiftCode());
        }
        return repository.save(swiftCode);
    }

    @Override
    public boolean delete(String swiftCode) {
        if (repository.findBySwiftCode(swiftCode).isEmpty()) {
            return false;
        }
        repository.deleteBySwiftCode(swiftCode);
        return true;
    }
}