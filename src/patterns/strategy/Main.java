package patterns.strategy;

import patterns.strategy.strategies.AggressiveStrategy;
import patterns.strategy.strategies.BalancedStrategy;
import patterns.strategy.strategies.DefensiveStrategy;

public class Main {
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