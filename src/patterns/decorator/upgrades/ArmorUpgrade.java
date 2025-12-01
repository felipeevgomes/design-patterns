package patterns.decorator.upgrades;
import patterns.decorator.TroopComponent;
import patterns.decorator.TroopDecorator;

public class ArmorUpgrade extends TroopDecorator {
  private static final int DEFENSE_BONUS = 15;
  private static final int UPGRADE_COST = 100;

  public ArmorUpgrade(TroopComponent troop){
    super(troop);
  }

  @Override
  public int getDefense(){
    return wrappedTroop.getDefense() + DEFENSE_BONUS;
  }

  @Override
  public int getCost(){
    return wrappedTroop.getCost() + UPGRADE_COST;
  }

  @Override
  public String getDescription(){
    return wrappedTroop.getDescription() + " + Armadura Reforçada";
  }

}
