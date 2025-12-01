package patterns.decorator;

import patterns.decorator.upgrades.ArmorUpgrade;
import patterns.decorator.upgrades.EliteTraining;
import patterns.decorator.upgrades.VeteranStatus;
import patterns.decorator.upgrades.WeaponUpgrade;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== DECORATOR PATTERN ===");
        
        // Criando uma tropa básica
        System.out.println("--- TROPA BÁSICA ---");
        TroopComponent basicSwordsman = new BasicTroop("Espadachim", 25, 20, 60);
        basicSwordsman.display();
        
        // Adicionando upgrade de armadura
        System.out.println("\n--- ADICIONANDO ARMADURA ---");
        TroopComponent armoredSwordsman = new ArmorUpgrade(basicSwordsman);
        armoredSwordsman.display();
        
        // Adicionando upgrade de arma (em cima da armadura)
        System.out.println("\n--- ADICIONANDO ARMA MELHORADA ---");
        TroopComponent fullyUpgraded = new WeaponUpgrade(armoredSwordsman);
        fullyUpgraded.display();
        
        // Adicionando treinamento elite
        System.out.println("\n--- ADICIONANDO TREINAMENTO ELITE ---");
        TroopComponent eliteSwordsman = new EliteTraining(fullyUpgraded);
        eliteSwordsman.display();
        
        // Adicionando status veterano
        System.out.println("\n--- PROMOVENDO A VETERANO ---");
        TroopComponent veteranSwordsman = new VeteranStatus(eliteSwordsman);
        veteranSwordsman.display();
        
        // Demonstrando diferentes combinações
        System.out.println("\n\n=== DIFERENTES COMBINAÇÕES DE UPGRADES ===");
        
        // Arqueiro com apenas arma
        System.out.println("\n--- ARQUEIRO COM ARMA APRIMORADA ---");
        TroopComponent archer = new BasicTroop("Arqueiro", 30, 10, 40);
        archer = new WeaponUpgrade(archer);
        archer.display();
        
        // Cavaleiro com armadura e treinamento
        System.out.println("\n--- CAVALEIRO COM ARMADURA E TREINAMENTO ---");
        TroopComponent knight = new BasicTroop("Cavaleiro", 40, 30, 120);
        knight = new ArmorUpgrade(knight);
        knight = new EliteTraining(knight);
        knight.display();
        
        // Comparação de custos
        System.out.println("\n\n=== ANÁLISE DE CUSTOS ===");
        TroopComponent basic = new BasicTroop("Soldado", 20, 15, 50);
        System.out.println("Soldado básico: " + basic.getCost() + " ouro");
        
        TroopComponent withArmor = new ArmorUpgrade(basic);
        System.out.println("Com armadura: " + withArmor.getCost() + " ouro");
        
        TroopComponent withWeapon = new WeaponUpgrade(withArmor);
        System.out.println("Com arma: " + withWeapon.getCost() + " ouro");
        
        TroopComponent fullUpgrade = new EliteTraining(withWeapon);
        System.out.println("Totalmente aprimorado: " + fullUpgrade.getCost() + " ouro");
        
        // Demonstrando a flexibilidade do padrão
        System.out.println("\n\n=== FLEXIBILIDADE DO DECORATOR ===");
        System.out.println("O padrão Decorator permite:");
        System.out.println("Adicionar funcionalidades em tempo de execução");
        System.out.println("Combinar múltiplos decoradores em qualquer ordem");
        System.out.println("Evitar explosão de subclasses");
        System.out.println("Manter o princípio Open/Closed (aberto para extensão, fechado para modificação)");
    }
}