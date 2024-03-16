package com.pedromonteiro.domain.castmember;

import java.util.Objects;
import java.util.UUID;

import com.pedromonteiro.domain.Identifier;
import com.pedromonteiro.domain.castmember.CastMemberID;

public class CastMemberID extends Identifier {

 private final String value;

    private CastMemberID(final String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static CastMemberID unique() {
        return CastMemberID.from(UUID.randomUUID());
    }

    public static CastMemberID from(final String anId) {
        return new CastMemberID(anId);
    }

     public static CastMemberID from(final UUID anId) {
        return new CastMemberID(anId.toString().toLowerCase());
    }

    public String getValue() {
        return value;
    }
    
      @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CastMemberID other = (CastMemberID) obj;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }
    
}
