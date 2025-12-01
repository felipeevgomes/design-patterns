package patterns.decorator;

public class TroopDecorator implements TroopComponent{
  protected TroopComponent wrappedTroop;

  public TroopDecorator(TroopComponent troop){
    this.wrappedTroop = troop;
  }

  @Override
  public int getAttack(){
    return wrappedTroop.getAttack();
  }

  @Override
  public int getDefense(){
    return wrappedTroop.getDefense();
  }
  
  @Override
  public int getCost(){
    return wrappedTroop.getCost();
  }
  
  @Override
  public String getDescription(){
    return wrappedTroop.getDescription();
  }

  @Override
  public void display(){
    wrappedTroop.display();
  }
}
