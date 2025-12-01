package patterns.decorator.upgrades;
import patterns.decorator.TroopComponent;
import patterns.decorator.TroopDecorator;

public class EliteTraining extends TroopDecorator {
    private static final int ATTACK_BONUS = 10;
    private static final int DEFENSE_BONUS = 10;
    private static final int TRAINING_COST = 200;
    
    public EliteTraining(TroopComponent troop) {
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
        return wrappedTroop.getCost() + TRAINING_COST;
    }
    
    @Override
    public String getDescription() {
        return wrappedTroop.getDescription() + " + Treinamento Elite";
    }
}
