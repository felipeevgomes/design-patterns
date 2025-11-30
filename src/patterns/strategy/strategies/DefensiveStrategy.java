package patterns.strategy.strategies;
import patterns.strategy.AttackStrategy;

public class DefensiveStrategy implements AttackStrategy {
    @Override
    public String execute(int basePower){
      int attackPower = (int)(basePower * getAttackModifier());
      return String.format("Ataque DEFENSIVO com poder %d! (-30%% ataque, +50%% defesa)", attackPower);
    }

    @Override
    public double getAttackModifier(){
      return 0.7;
    }

    @Override
    public double getDefenseModifier(){
      return 1.5;
    }

    @Override
    public String getName(){
      return "Defensiva";
    }
}

