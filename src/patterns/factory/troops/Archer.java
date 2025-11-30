package patterns.factory.troops;
import patterns.factory.Troop;

public class Archer implements Troop {
  private final String civilization;
  private final int attack;
  private final int defense;
  private final int goldCost;
  private final int woodCost;

  public Archer(String civilization, int attack, int defense, int goldCost, int woodCost){
    this.civilization = civilization;
    this.attack = attack;
    this.defense = defense;
    this.goldCost = goldCost;
    this.woodCost = woodCost;
  }

  @Override
  public int getAttack(){
    return attack;
  }

  @Override
  public int getDefense(){
    return defense;
  }
  
  @Override
  public int getGoldCost(){
    return goldCost;
  }
  
  @Override
  public int getWoodCost(){
    return woodCost;
  }
  
  @Override
  public String getDescription(){
    return String.format("Arqueiro %s (Ataque: %d, Defesa: %d, Custo: %d ouro, %d madeira)" , civilization, attack, defense, goldCost, woodCost);
  }

  @Override
  public void display(){
    System.err.println(getDescription());
  }
}
