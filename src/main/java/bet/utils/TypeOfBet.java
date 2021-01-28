package bet.utils;

public enum TypeOfBet {
    EVEN, ODD;

    public static TypeOfBet byName(final String name) {
        for (TypeOfBet betType: values()){
            if(betType.name().equals(name)) return betType;
        }
         return null;
    }
}
