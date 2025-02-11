package tableIdentifier;

public class IdentifierEntry {
    private String name;
    private IdentifierType type;
    private Integer value;
    private Integer address;
    private Integer varType;
    private Integer dimension;
    private String passageMode;
    private Integer parameterCount;
    private Integer returnType;

    public IdentifierEntry(String name, IdentifierType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public IdentifierType getType() {
        return type;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getAddress() {
        return address;
    }

    public void setAddress(Integer address) {
        this.address = address;
    }

    public Integer getVarType() {
        return varType;
    }

    public void setVarType(Integer varType) {
        this.varType = varType;
    }

    public Integer getDimension() {
        return dimension;
    }

    public void setDimension(Integer dimension) {
        this.dimension = dimension;
    }

    public String getPassageMode() {
        return passageMode;
    }

    public void setPassageMode(String passageMode) {
        this.passageMode = passageMode;
    }

    public Integer getParameterCount() {
        return parameterCount;
    }

    public void setParameterCount(Integer parameterCount) {
        this.parameterCount = parameterCount;
    }

    public Integer getReturnType() {
        return returnType;
    }

    public void setReturnType(Integer returnType) {
        this.returnType = returnType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name)
          .append(", Type: ").append(type);

        switch (type) {
            case CONSTANT -> sb.append(", Value: ").append(value);
            case VARIABLE -> {
                sb.append(", Address: ").append(address);
                if (dimension != null) {
                    sb.append(", Dimension: ").append(dimension);
                }
            }
            case FUNCTION -> {
                sb.append(", ParameterCount: ").append(parameterCount)
                  .append(", ReturnType: ").append(returnType);
                if (passageMode != null) {
                    sb.append(", PassageMode: ").append(passageMode);
                }
            }
        }

        if (varType != null) {
            sb.append(", VarType: ").append(varType);
        }

        return sb.toString();
    }
}