package patterns.factory.civilizations;
import  patterns.factory.TroopFactory;
import patterns.factory.Troop;
import patterns.factory.troops.Archer;
import patterns.factory.troops.Swordsman;
import patterns.factory.troops.Knight;

public class BritishFactory extends  TroopFactory {
  @Override
  public Troop createArcher(){
  return new Archer("Britanico", 35, 10, 40, 20);
  }

  @Override
  public Troop createSwordsman(){
    return new Swordsman("Britanico", 25, 20, 60, 10);
  }

  @Override
  public Troop createKnight(){
    return new Knight("Britanico", 40, 30, 120, 0);
  }
}
