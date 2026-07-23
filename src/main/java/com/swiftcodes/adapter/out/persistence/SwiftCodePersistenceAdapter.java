package com.swiftcodes.adapter.out.persistence;

import com.swiftcodes.application.port.out.SwiftCodeRepository;
import com.swiftcodes.domain.model.SwiftCode;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
class SwiftCodePersistenceAdapter implements SwiftCodeRepository {

    private final SwiftCodeJpaRepository jpaRepository;

    SwiftCodePersistenceAdapter(SwiftCodeJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<SwiftCode> findBySwiftCode(String swiftCode) {
        return jpaRepository.findById(swiftCode)
                .map(SwiftCodeMapper::toDomain);
    }

    @Override
    public List<SwiftCode> findByCountryIso2(String countryIso2) {
        return jpaRepository.findByCountryIso2(countryIso2)
                .stream()
                .map(SwiftCodeMapper::toDomain)
                .toList();
    }

    @Override
    public SwiftCode save(SwiftCode swiftCode) {
        SwiftCodeEntity entity = SwiftCodeMapper.toEntity(swiftCode);
        SwiftCodeEntity saved = jpaRepository.save(entity);
        return SwiftCodeMapper.toDomain(saved);
    }

    @Override
    public void deleteBySwiftCode(String swiftCode) {
        jpaRepository.deleteById(swiftCode);
    }
    @Override
    public long count() {
        return jpaRepository.count();
    }
}