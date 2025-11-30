package patterns.strategy.strategies;
import patterns.strategy.AttackStrategy;

public class BalancedStrategy implements AttackStrategy {
  @Override
    public String execute(int basePower){
      int attackPower = (int)(basePower * getAttackModifier());
      return String.format("Ataque BALANCEADO com poder %d! (ataque e defesa equilibrados)", attackPower);
    }

    @Override
    public double getAttackModifier(){
      return 1.0;
    }

    @Override
    public double getDefenseModifier(){
      return 1.0;
    }

    @Override
    public String getName(){
      return "Balanceada";
    }
}
