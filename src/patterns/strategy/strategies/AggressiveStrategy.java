package patterns.strategy.strategies;
import patterns.strategy.AttackStrategy;

public class AggressiveStrategy implements AttackStrategy {
    @Override
    public String execute(int basePower){
      int attackPower = (int)(basePower * getAttackModifier());
      return String.format("Ataque AGRESSIVO com poder %d! (+50%% ataque, -30%% defesa)", attackPower);
    }

    @Override
    public double getAttackModifier(){
      return 1.5;
    }

    @Override
    public double getDefenseModifier(){
      return 0.7;
    }

    @Override
    public String getName(){
      return "Agressiva";
    }
}
