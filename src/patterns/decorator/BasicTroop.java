package patterns.decorator;

public class BasicTroop implements TroopComponent {
  private final  String name;
  private final int attack;
  private final int defense;
  private final int cost;

  public BasicTroop(String name, int attack, int defense, int cost){
    this.name = name;
    this.attack = attack;
    this.defense = defense;
    this.cost = cost;
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
  public int getCost(){
    return cost;
  }

  @Override
  public String getDescription(){
    return name;
  }

  @Override
  public void display(){
    System.out.println("TROPA: " + String.format("%-30s", getDescription()) );
    System.out.println("Ataque: " + String.format("%-27d", getAttack()) );
    System.out.println("Defesa: " + String.format("%-27d", getDefense()) );
    System.out.println("Custo: " + String.format("%-29d", getCost()) );
  }
}
