package com.vasileva.task310.repository;

import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import java.util.Locale;

public class TblPhysicalNamingStrategy extends CamelCaseToUnderscoresNamingStrategy {
    private static final String TABLE_PREFIX = "tbl_";

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        if (name == null) {
            return null;
        }
        String prefixedName = TABLE_PREFIX + name.getText().toLowerCase(Locale.ROOT);
        return Identifier.toIdentifier(prefixedName);
    }
}
