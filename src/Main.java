import patterns.factory.TroopFactory;
import patterns.factory.Troop;
import patterns.factory.civilizations.BritishFactory;
import patterns.factory.civilizations.FrenchFactory;

class FactoryMethodDemo {
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
}