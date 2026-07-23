package com.swiftcodes.adapter.out.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "swift_codes")
public class SwiftCodeEntity {

    @Id
    @Column(name = "swift_code")
    private String swiftCode;

    @Column(name = "code_type")
    private String codeType;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "country_iso2")
    private String countryIso2;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "town_name")
    private String townName;

    @Column(name = "time_zone")
    private String timeZone;

    protected SwiftCodeEntity() {
    }

    public SwiftCodeEntity(String swiftCode, String codeType, String name, String address,
                           String countryIso2, String countryName, String townName, String timeZone) {
        this.swiftCode = swiftCode;
        this.codeType = codeType;
        this.name = name;
        this.address = address;
        this.countryIso2 = countryIso2;
        this.countryName = countryName;
        this.townName = townName;
        this.timeZone = timeZone;
    }

    public String getSwiftCode() { return swiftCode; }
    public String getCodeType() { return codeType; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getCountryIso2() { return countryIso2; }
    public String getCountryName() { return countryName; }
    public String getTownName() { return townName; }
    public String getTimeZone() { return timeZone; }
}