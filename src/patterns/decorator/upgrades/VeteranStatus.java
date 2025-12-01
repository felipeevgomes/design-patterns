package patterns.decorator.upgrades;
import patterns.decorator.TroopComponent;
import patterns.decorator.TroopDecorator;

public class VeteranStatus extends TroopDecorator {
    private static final int ATTACK_BONUS = 15;
    private static final int DEFENSE_BONUS = 15;
    private static final int VETERAN_COST = 0; //ganho apos a batalha
    
    public VeteranStatus(TroopComponent troop) {
        super(troop);
    }
    
    @Override
    public int getAttack() {
        return wrappedTroop.getAttack() + ATTACK_BONUS;
    }
    
    @Override
    public int getDefense() {
        return wrappedTroop.getDefense() + DEFENSE_BONUS;
    }
    
    @Override
    public int getCost() {
        return wrappedTroop.getCost() + VETERAN_COST;
    }
    
    @Override
    public String getDescription() {
        return wrappedTroop.getDescription() + " + Status Veterano";
    }
}
