import patterns.decorator.*;
import patterns.decorator.upgrades.*;
import patterns.factory.*;
import patterns.factory.civilizations.*;
import patterns.strategy.*;
import patterns.strategy.strategies.*;

public class Main {
    
    public static void main(String[] args) {
        if (args.length == 0) {
            demonstrateAll();
            return;
        }
        
        String pattern = args[0].toLowerCase();
        
        switch(pattern) {
            case "factory" -> demonstrateFactoryMethod();
            case "strategy" -> demonstrateStrategy();
            case "decorator" -> demonstrateDecorator();
            case "all" -> demonstrateAll();
            default -> System.out.println("Uso: java Main [factory|strategy|decorator|all]");
        }
    }
    
    private static void demonstrateFactoryMethod() {
        printHeader("FACTORY METHOD PATTERN");
        
        System.out.println("CIVILIZAÇÃO BRITÂNICA");
        TroopFactory britishFactory = new BritishFactory();
        britishFactory.trainArmy();
        
        System.out.println("\nCIVILIZAÇÃO FRANCESA");
        TroopFactory frenchFactory = new FrenchFactory();
        frenchFactory.trainArmy();
        
        System.out.println("\n=== Comparando Arqueiros ===");
        Troop britishArcher = britishFactory.createArcher();
        Troop frenchArcher = frenchFactory.createArcher();
        
        System.out.println("Britânico: " + britishArcher.getAttack() + " ataque");
        System.out.println("Francês: " + frenchArcher.getAttack() + " ataque");
    }
    
    private static void demonstrateStrategy() {
        printHeader("STRATEGY PATTERN");
        
        Civilization romans = new Civilization("Romanos", 1000);
        
        System.out.println("FASE 1: INÍCIO DO JOGO");
        romans.displayInfo();
        romans.attack();
        
        System.out.println("\nFASE 2: RUSH OFFENSIVO");
        romans.setStrategy(new AggressiveStrategy());
        romans.displayInfo();
        romans.addResources(200);
        romans.attack();
        
        System.out.println("\nFASE 3: CONSOLIDANDO DEFESAS");
        romans.setStrategy(new DefensiveStrategy());
        romans.displayInfo();
        romans.addResources(200);
        romans.attack();
    }
    
    private static void demonstrateDecorator() {
        printHeader("DECORATOR PATTERN");
        
        System.out.println("EVOLUÇÃO DE UM ESPADACHIM\n");
        
        TroopComponent troop = new BasicTroop("Espadachim", 25, 20, 60);
        System.out.println("Nível 1: RECRUTA");
        troop.display();
        
        System.out.println("\nAplicando Upgrade de Armadura...");
        troop = new ArmorUpgrade(troop);
        System.out.println("Nível 2: SOLDADO");
        troop.display();
        
        System.out.println("\nAplicando Upgrade de Arma...");
        troop = new WeaponUpgrade(troop);
        System.out.println("Nível 3: GUERREIRO");
        troop.display();
        
        System.out.println("\nAplicando Treinamento Elite...");
        troop = new EliteTraining(troop);
        System.out.println("Nível 4: GUARDA DE ELITE");
        troop.display();
        
        System.out.println("\nPromovendo a Veterano...");
        troop = new VeteranStatus(troop);
        System.out.println("Nível 5: VETERANO DE GUERRA");
        troop.display();
    }
    
    private static void demonstrateAll() {
        printHeader("DEMONSTRAÇÃO COMPLETA - TODOS OS PADRÕES");
        
        // FACTORY METHOD
        System.out.println("\nFASE 1: CRIAÇÃO DE EXÉRCITOS (Factory Method)\n");
        
        TroopFactory britishFactory = new BritishFactory();
        TroopFactory frenchFactory = new FrenchFactory();
        
        System.out.println("Britânicos criando exército:");
        Troop britishArcher = britishFactory.createArcher();
        britishArcher.display();
        
        System.out.println("\nFranceses criando exército:");
        Troop frenchKnight = frenchFactory.createKnight();
        frenchKnight.display();
        
        // DECORATOR
        System.out.println("\n\nFASE 2: MELHORANDO TROPAS (Decorator)\n");
        
        System.out.println("Melhorando arqueiro britânico:");
        TroopComponent upgradedArcher = new BasicTroop("Arqueiro Britânico", britishArcher.getAttack(), britishArcher.getDefense(), britishArcher.getGoldCost());
        upgradedArcher = new WeaponUpgrade(upgradedArcher);
        upgradedArcher = new EliteTraining(upgradedArcher);
        upgradedArcher.display();
        
        System.out.println("\nMelhorando cavaleiro francês:");
        TroopComponent upgradedKnight = new BasicTroop("Cavaleiro Francês", frenchKnight.getAttack(), frenchKnight.getDefense(), frenchKnight.getGoldCost());
        upgradedKnight = new ArmorUpgrade(upgradedKnight);
        upgradedKnight = new VeteranStatus(upgradedKnight);
        upgradedKnight.display();
        
        // STRATEGY
        System.out.println("\n\nFASE 3: ESTRATÉGIAS DE COMBATE (Strategy)\n");
        
        Civilization british = new Civilization("Inglaterra", 2000);
        Civilization french = new Civilization("França", 2000);
        
        System.out.println("Inglaterra adota estratégia agressiva:");
        british.setStrategy(new AggressiveStrategy());
        british.displayInfo();
        british.attack();
        
        System.out.println("\nFrança responde com estratégia defensiva:");
        french.setStrategy(new DefensiveStrategy());
        french.displayInfo();
        french.attack();
        
        System.out.println("\n\nFASE 4: AJUSTES TÁTICOS\n");
        
        british.setStrategy(new BalancedStrategy());
        System.out.println("Inglaterra muda para estratégia balanceada:");
        british.attack();
        
        french.setStrategy(new AggressiveStrategy());
        System.out.println("\nFrança contra-ataca com estratégia agressiva:");
        french.attack();
    }
    
    private static void printHeader(String title) {
        int width = 60;
        int padding = (width - title.length() - 2) / 2;
        
        System.out.println("\n" + "═".repeat(width));
        System.out.println( " ".repeat(padding) + title + " ".repeat(width - padding - title.length() - 2) );
        System.out.println("═".repeat(width));
    }
}