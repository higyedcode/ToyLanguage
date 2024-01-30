package org.model.values;

import org.model.types.RefType;
import org.model.types.Type;

public class RefValue implements Value{
    int address;
    Type locationType;

    public RefValue(int address, Type locationType) {
        this.address = address;
        this.locationType = locationType;
    }

    public int getAddress() {
        return address;
    }

    public Type getLocationType() {
        return locationType;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public void setLocationType(Type locationType) {
        this.locationType = locationType;
    }

    @Override
    public boolean equals(Object another) {
        if (another instanceof RefValue) {
            return address == ((RefValue) another).getAddress() && locationType.equals(((RefValue) another).getLocationType());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "(" + address + ", " + locationType.toString() + ")";
    }

    @Override
    public Type getType() {
        return new RefType(locationType);
    }

    @Override
    public Value deepcopy() {
        return new RefValue(address, locationType.deepcopy());
    }
}
