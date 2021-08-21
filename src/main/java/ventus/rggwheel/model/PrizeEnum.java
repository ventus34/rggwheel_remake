package ventus.rggwheel.model;

public enum PrizeEnum {
    ADD_GAME("+ Game", "Add one game on current platform.", "+ Game"),
    CHAT_CONSOLE("Chat Console", "The next game will be a game from a platform chosen by chat, still counting in the current platform, if it happens to be the last platform then spin the wheel again.", "Chat Platform"),
    RESET("Reset Incentives", "Subs and donation incentives goals on the current platform reset to starting value. (this includes current donations and subs points).", "Reset"),
    MASTER("Master Choice", "The next game is chosen by the player in the current platform.", "Master"),
    TREASURE("Golden Treasure Bag", "The last donator chooses the next game in the current platform.", "Treasure"),
    LUCKY_SUB("Golden Sub Bag", "The last subscriber chooses the next game in the current platform.", "Golden Sub"),
    HELLO_KITTY("Cat in the Bag", "Next game will be chosen randomly from a list that includes all the games on each platform.", "Cat in the bag"),
    SPIN_AGAIN("Juan More Time", "The player spins the wheel one more time.", "Spin again"),
    HEADS_OR_TAILS("Heads or Tails", "Player makes a guess head or tail, then throw the coin. If he wins then one game is subtracted, but if he loses then one game is added.", "Heads/Tails"),
    DOUBLE("Super Wheel", "The player spins the wheel again but with multiplier by two effect.", "Double"),
    CHAT_PICK("Chat Pick", "The player rolls “next game” in the generator and the audience can pick one from the 5 games shown on screen.", "Chat Pick"),
    JUAN_OF_FIVE("Juan of Five", "The player rolls next game in the generator and can pick one from the 5 games shown on screen.", "Juan of five"),
    FIVE_HINTS("Consult Chat", "Item goes to inventory, when used audience spoilers will be allowed for the current game.", "Five hints"),
    POTION("Reroll Potion", "Item goes to inventory, when used the player can reroll the current game without any punishment.", "Potion"),
    MS_DOS("MS-DOS", "Next game is a DOS game", "MS-DOS"),
    NOTHING("Nothing", "Sometimes the “house wins”.", "Nothing"),
    TIER_PLUS("Tier +", "The incentives will increase to the next level tier. If the sub incentive is currently at “10/15” then it will increase to “10/18”, the same will happen with the bits/donations.", "Tier +"),
    SELLOUT("- 50% Sellout", "The $$$ donations and incentive will be halved and it will stay that way across games and platforms. It will only be restored to original value after incentive is met.", "-50% Sellout");

    final String name;
    final String description;
    final String label;

    PrizeEnum(String name, String description, String label) {
        this.name = name;
        this.description = description;
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLabel() {
        return label;
    }
}
