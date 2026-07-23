package com.swiftcodes.adapter.out.persistence;

import com.swiftcodes.domain.model.SwiftCode;

    class SwiftCodeMapper {

        static SwiftCode toDomain(SwiftCodeEntity entity) {
            return new SwiftCode(
                    entity.getSwiftCode(),
                    entity.getCodeType(),
                    entity.getName(),
                    entity.getAddress(),
                    entity.getCountryIso2(),
                    entity.getCountryName(),
                    entity.getTownName(),
                    entity.getTimeZone()
            );
        }

        static SwiftCodeEntity toEntity(SwiftCode domain) {
            return new SwiftCodeEntity(
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

        private SwiftCodeMapper() {
        }
    }

