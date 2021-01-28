package bet;

public interface BetType {

    String typeOfBet();
    boolean betMatch(BetType other);
    double amountDueToPlayer();

    String betTypeAsString();
}
