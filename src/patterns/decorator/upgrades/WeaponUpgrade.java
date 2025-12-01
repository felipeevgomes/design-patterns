package patterns.decorator.upgrades;
import patterns.decorator.TroopComponent;
import patterns.decorator.TroopDecorator;

public class WeaponUpgrade extends TroopDecorator {
  public static final int ATTACK_BONUS = 20;
  public static final int UPGRADE_COST = 150;

  public WeaponUpgrade(TroopComponent troop){
    super(troop);
  }

  @Override
  public int getAttack(){
    return wrappedTroop.getAttack() + ATTACK_BONUS;
  }

  @Override
  public int getCost(){
    return wrappedTroop.getCost() + UPGRADE_COST;
  }

  @Override
  public String getDescription(){
    return wrappedTroop.getDescription() + " + Arma Aprimorada";
  }
}
