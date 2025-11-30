import patterns.factory.TroopFactory;
import patterns.factory.Troop;
import patterns.factory.civilizations.BritishFactory;
import patterns.factory.civilizations.FrenchFactory;
import patterns.strategy.Civilization;
import patterns.strategy.strategies.BalancedStrategy;
import patterns.strategy.strategies.AggressiveStrategy;
import patterns.strategy.strategies.DefensiveStrategy;

/*class FactoryMethodDemo {
    public static void main(String[] args) {
        System.out.println("=== FACTORY METHOD PATTERN ===");
        System.out.println("Referência: https://refactoring.guru/pt-br/design-patterns/factory-method\n");
        
        // Criando exército britânico
        System.out.println(" CIVILIZAÇÃO BRITÂNICA");
        TroopFactory britishFactory = new BritishFactory();
        britishFactory.trainArmy();
        
        // Criando exército francês
        System.out.println("\n CIVILIZAÇÃO FRANCESA");
        TroopFactory frenchFactory = new FrenchFactory();
        frenchFactory.trainArmy();
        
        // Demonstrando a vantagem do padrão: código desacoplado
        System.out.println("\n=== Comparando Arqueiros ===");
        Troop britishArcher = britishFactory.createArcher();
        Troop frenchArcher = frenchFactory.createArcher();
        
        System.out.println("Britânico: " + britishArcher.getAttack() + " de ataque");
        System.out.println("Francês: " + frenchArcher.getAttack() + " de ataque");
    }
}*/

class StrategyDemo {
    public static void main(String[] args) {
        System.out.println("=== STRATEGY PATTERN ===");      
      
        // Criando uma civilização
        Civilization romans = new Civilization("Romanos", 500);
        romans.displayInfo();
        
        // Testando estratégia padrão (Balanceada)
        System.out.println("\n--- Fase 1: Início do jogo (Estratégia Balanceada) ---");
        romans.attack();
        
        // Mudando para estratégia agressiva
        System.out.println("\n--- Fase 2: Rush offensivo (Estratégia Agressiva) ---");
        romans.setStrategy(new AggressiveStrategy());
        romans.displayInfo();
        romans.addResources(100);
        romans.attack();
        
        // Mudando para estratégia defensiva
        System.out.println("\n--- Fase 3: Consolidando território (Estratégia Defensiva) ---");
        romans.setStrategy(new DefensiveStrategy());
        romans.displayInfo();
        romans.addResources(100);
        romans.attack();
        
        // Voltando para estratégia balanceada
        System.out.println("\n--- Fase 4: Final do jogo (Estratégia Balanceada) ---");
        romans.setStrategy(new BalancedStrategy());
        romans.displayInfo();
        romans.addResources(100);
        romans.attack();
        
        // Demonstrando múltiplas civilizações com estratégias diferentes
        System.out.println("\n\n=== Comparando Civilizações ===");
        Civilization vikings = new Civilization("Vikings", 400);
        vikings.setStrategy(new AggressiveStrategy());
        
        Civilization byzantines = new Civilization("Bizantinos", 600);
        byzantines.setStrategy(new DefensiveStrategy());
        
        System.out.println("\nAtaques simultâneos:");
        vikings.attack();
        byzantines.attack();
    }
}