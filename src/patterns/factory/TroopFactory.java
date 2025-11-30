package patterns.factory;

public abstract class TroopFactory {
  public abstract Troop createArcher();
  public abstract Troop createSwordsman();
  public abstract Troop createKnight();

  public void trainArmy(){
    System.out.println("--Treinando exército--");
    Troop archer = createArcher();
    Troop swordsman = createSwordsman();
    Troop knight = createKnight();

    archer.display();
    swordsman.display();
    knight.display();
    
  }
}
