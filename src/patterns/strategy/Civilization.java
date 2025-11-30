package patterns.strategy;

import patterns.strategy.strategies.BalancedStrategy;

public class Civilization {
  private String name;
  private int militaryPower;
  private int resources;
  private AttackStrategy strategy;
  /**
  * Classe Civilization (Contexto do padrão Strategy).
  * 
  * Esta classe usa uma estratégia de ataque que pode ser trocada
  * dinamicamente durante o jogo, permitindo que a civilização
  * adapte sua forma de combate conforme necessário.
  */
  public Civilization(String name, int resources){
    this.name = name;
    this.militaryPower = 100;
    this.resources = resources;
    this.strategy = new BalancedStrategy();
  }

  //Trocar a estrategia em tempo de execução
  public void setStrategy(AttackStrategy strategy){
    this.strategy = strategy;
    System.out.println(name + "mudou de estratégia: " + strategy.getName());
  }

  //executa um ataque usando a estrategia atual
  public void attack(){
    if(resources < 50){
      System.err.println("Rescursos insuficientes para atacar!");
      return;
    }
    resources -=50;
    String result = strategy.execute(militaryPower);
    System.out.println(name + "->" + result );
    System.out.println(" Rescursos restantes: " + resources);
  }

  public void displayInfo(){
    System.out.println("CIVILIZAÇÃO: " + String.format("%-24s", name)  );
    System.out.println("Poder Militar: " + String.format("%-20d", militaryPower) );
    System.out.println("Recursos: " + String.format("%-25d", resources));
    System.out.println("Estratégia: " + String.format("%-23s", strategy.getName()));
    System.out.println("Modificador Ataque: " + String.format("%-15.0f%%", (strategy.getAttackModifier() * 100)) );
    System.out.println("Modificador Defesa: " + String.format("%-15.0f%%", (strategy.getDefenseModifier() * 100)) );
  }

  public void addResources(int amount){
    resources += amount;
    System.out.println(name + " ganhou " + amount + " recursos!");
  }

  public String getName(){
    return name;
  }

  public AttackStrategy getStrategy(){
    return strategy;
  }


}
