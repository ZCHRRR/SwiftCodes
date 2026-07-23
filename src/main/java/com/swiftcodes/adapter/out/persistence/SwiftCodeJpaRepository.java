package com.swiftcodes.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface SwiftCodeJpaRepository extends JpaRepository<SwiftCodeEntity, String> {

    List<SwiftCodeEntity> findByCountryIso2(String countryIso2);
}