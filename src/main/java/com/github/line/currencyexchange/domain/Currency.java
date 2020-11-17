package com.github.line.currencyexchange.domain;

import jdk.nashorn.internal.ir.annotations.Immutable;

import java.util.Objects;

@Immutable
public class Currency {
    private final String name;
    private final String code;

    public Currency(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.code);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof Currency)){
            return false;
        }

        Currency _obj = (Currency) obj;
        return Objects.equals(this.code, _obj.getCode())
                && Objects.equals(this.name, _obj.getName());
    }

    @Override
    public String toString() {
        return "Currency{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
