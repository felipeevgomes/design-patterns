package patterns.factory.civilizations;
import  patterns.factory.TroopFactory;
import patterns.factory.Troop;
import patterns.factory.troops.Archer;
import patterns.factory.troops.Swordsman;
import patterns.factory.troops.Knight;


public class FrenchFactory extends TroopFactory {
   @Override
  public Troop createArcher(){
  return new Archer("Frances", 30, 10, 40, 20);
  }

  @Override
  public Troop createSwordsman(){
    return new Swordsman("Frances", 25, 20, 60, 10);
  }

  @Override
  public Troop createKnight(){
    return new Knight("Frances", 50, 35, 120, 0);
  }
}

