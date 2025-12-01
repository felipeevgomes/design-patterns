# Decorator Pattern (Padrão Estrutural)

## 📚 Referência
Este padrão é baseado no catálogo do [Refactoring Guru - Decorator](https://refactoring.guru/pt-br/design-patterns/decorator)

## 🎯 Propósito
O Decorator é um padrão de projeto estrutural que permite adicionar novos comportamentos a objetos colocando-os dentro de objetos wrapper (invólucros) especiais que contêm esses comportamentos.

## 🤖 LLM Utilizada
**Claude (Anthropic) - Sonnet 4.5** foi utilizada na implementação dos exemplos e na estruturação do código.
<br>
**Google Gemini - 3 PRO** foi utilizado para geração de boilerplate de código Java, elaboração de cenários "Age of Empires" e estruturação da documentação Markdown.

## ❓ Problema
No contexto do Age of Empires, tropas podem receber diversos upgrades:
- Armadura Reforçada (+15 defesa, +100 ouro)
- Arma Aprimorada (+20 ataque, +150 ouro)
- Treinamento Elite (+10 ataque, +10 defesa, +200 ouro)
- Status Veterano (+15 ataque, +15 defesa, grátis)

**Exemplo do problema sem o padrão (usando herança):**

```
BasicTroop
├── TroopWithArmor
│   ├── TroopWithArmorAndWeapon
│   │   ├── TroopWithArmorWeaponAndElite
│   │   │   └── TroopWithArmorWeaponEliteAndVeteran
│   │   └── TroopWithArmorWeaponAndVeteran
│   ├── TroopWithArmorAndElite
│   │   └── TroopWithArmorEliteAndVeteran
│   └── TroopWithArmorAndVeteran
├── TroopWithWeapon
│   ├── TroopWithWeaponAndElite
│   │   └── TroopWithWeaponEliteAndVeteran
│   └── TroopWithWeaponAndVeteran
├── TroopWithElite
│   └── TroopWithEliteAndVeteran
└── TroopWithVeteran
```

**Com 4 upgrades = 16 combinações = 16 classes!**  
**Com 5 upgrades = 32 combinações!** 🤯

**Problemas:**
- ❌ Explosão de subclasses
- ❌ Código duplicado
- ❌ Difícil manter
- ❌ Impossível combinar upgrades em ordem diferente

## ✅ Solução
O padrão Decorator resolve isso através de:

1. **Interface comum** (`TroopComponent`) para componentes e decoradores
2. **Componente base** (`BasicTroop`) - objeto a ser decorado
3. **Decorador abstrato** (`TroopDecorator`) - base para todos os decoradores
4. **Decoradores concretos** (ArmorUpgrade, WeaponUpgrade, etc.) - adicionam funcionalidades
5. **Composição recursiva** - decoradores envolvem componentes ou outros decoradores

## 📊 Diagrama UML

```
        ┌────────────────────┐
        │  TroopComponent    │ (Interface)
        ├────────────────────┤
        │+ getAttack(): int  │
        │+ getDefense(): int │
        │+ getCost(): int    │
        │+ getDescription()  │
        │+ display()         │
        └──────────┬─────────┘
                   △
                   │ implements
        ┌──────────┴─────────────────────┐
        │                                │
┌───────▼─────────┐            ┌─────────▼─────────┐
│   BasicTroop    │            │  TroopDecorator   │ (Abstract)
├─────────────────┤            ├───────────────────┤
│- name: String   │            │# wrappedTroop:    │◆───┐
│- attack: int    │            │  TroopComponent   │    │
│- defense: int   │            ├───────────────────┤    │
│- cost: int      │            │+ getAttack()      │    │
├─────────────────┤            │+ getDefense()     │    │
│+ getAttack()    │            │+ getCost()        │    │
│+ getDefense()   │            │+ getDescription() │    │
│+ getCost()      │            │+ display()        │    │
│+ getDescription()│           └─────────┬─────────┘    │
│+ display()      │                      △              │
└─────────────────┘                      │              │
                              ┌──────────┼──────────┐   │
                              │          │          │   │
                     ┌────────▼─────┐ ┌──▼─────────▼┐ ┌▼──────────┐
                     │ArmorUpgrade  │ │WeaponUpgrade│ │EliteTraining│
                     ├──────────────┤ ├─────────────┤ ├────────────┤
                     │+getDefense():│ │+getAttack():│ │+getAttack()│
                     │ base + 15    │ │ base + 20   │ │ base + 10  │
                     │+getCost():   │ │+getCost():  │ │+getDefense()│
                     │ base + 100   │ │ base + 150  │ │ base + 10  │
                     └──────────────┘ └─────────────┘ │+getCost(): │
                                                      │ base + 200 │
                                                      └────────────┘
```

### Explicação do Diagrama:
- **TroopComponent**: Interface comum para componentes e decoradores
- **BasicTroop**: Componente concreto (tropa base)
- **TroopDecorator**: Decorador abstrato que mantém referência ao componente
- **Decoradores Concretos**: Adicionam funcionalidades específicas
- **Composição (◆)**: Decorador "envolve" um componente

## 💻 Estrutura do Código

### 1. Interface Componente (`TroopComponent.java`)
```java
public interface TroopComponent {
    int getAttack();
    int getDefense();
    int getCost();
    String getDescription();
    void display();
}
```
**Explicação**: Define o contrato comum para tropas base e decoradas.

### 2. Componente Concreto (`BasicTroop.java`)
```java
public class BasicTroop implements TroopComponent {
    private String name;
    private int attack;
    private int defense;
    private int cost;
    
    public BasicTroop(String name, int attack, int defense, int cost) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.cost = cost;
    }
    
    @Override
    public int getAttack() { return attack; }
    
    @Override
    public int getDefense() { return defense; }
    
    @Override
    public int getCost() { return cost; }
    
    @Override
    public String getDescription() { return name; }
}
```
**Explicação**: Implementação base de uma tropa sem upgrades.

### 3. Decorador Abstrato (`TroopDecorator.java`)
```java
public abstract class TroopDecorator implements TroopComponent {
    protected TroopComponent wrappedTroop; // Referência ao componente
    
    public TroopDecorator(TroopComponent troop) {
        this.wrappedTroop = troop;
    }
    
    // Delega chamadas ao componente envolvido
    @Override
    public int getAttack() {
        return wrappedTroop.getAttack();
    }
    
    @Override
    public int getDefense() {
        return wrappedTroop.getDefense();
    }
    
    @Override
    public int getCost() {
        return wrappedTroop.getCost();
    }
    
    @Override
    public String getDescription() {
        return wrappedTroop.getDescription();
    }
}
```
**Explicação**: Base para todos os decoradores. Mantém referência ao componente envolvido e delega chamadas a ele.

### 4. Decoradores Concretos

#### Armadura Reforçada (`ArmorUpgrade.java`)
```java
public class ArmorUpgrade extends TroopDecorator {
    private static final int DEFENSE_BONUS = 15;
    private static final int UPGRADE_COST = 100;
    
    public ArmorUpgrade(TroopComponent troop) {
        super(troop);
    }
    
    @Override
    public int getDefense() {
        return wrappedTroop.getDefense() + DEFENSE_BONUS; // Adiciona bônus
    }
    
    @Override
    public int getCost() {
        return wrappedTroop.getCost() + UPGRADE_COST; // Adiciona custo
    }
    
    @Override
    public String getDescription() {
        return wrappedTroop.getDescription() + " + Armadura Reforçada";
    }
}
```
**Explicação**: Adiciona defesa e custo ao componente envolvido.

#### Arma Aprimorada (`WeaponUpgrade.java`)
```java
public class WeaponUpgrade extends TroopDecorator {
    private static final int ATTACK_BONUS = 20;
    private static final int UPGRADE_COST = 150;
    
    public WeaponUpgrade(TroopComponent troop) {
        super(troop);
    }
    
    @Override
    public int getAttack() {
        return wrappedTroop.getAttack() + ATTACK_BONUS;
    }
    
    @Override
    public int getCost() {
        return wrappedTroop.getCost() + UPGRADE_COST;
    }
    
    @Override
    public String getDescription() {
        return wrappedTroop.getDescription() + " + Arma Aprimorada";
    }
}
```
**Explicação**: Adiciona ataque e custo ao componente envolvido.

### 5. Uso do Padrão
```java
// Criar tropa básica
TroopComponent troop = new BasicTroop("Espadachim", 25, 20, 60);
// Ataque: 25, Defesa: 20, Custo: 60

// Adicionar armadura
troop = new ArmorUpgrade(troop);
// Ataque: 25, Defesa: 35, Custo: 160

// Adicionar arma
troop = new WeaponUpgrade(troop);
// Ataque: 45, Defesa: 35, Custo: 310

// Adicionar treinamento
troop = new EliteTraining(troop);
// Ataque: 55, Defesa: 45, Custo: 510

// Promover a veterano
troop = new VeteranStatus(troop);
// Ataque: 70, Defesa: 60, Custo: 510

System.out.println(troop.getDescription());
// "Espadachim + Armadura Reforçada + Arma Aprimorada 
//  + Treinamento Elite + Status Veterano ⭐"
```

## 📊 Tabela de Upgrades

| Upgrade | Bônus Ataque | Bônus Defesa | Custo | Quando Usar |
|---------|--------------|--------------|-------|-------------|
| **Armadura** | - | +15 | +100 | Unidades defensivas |
| **Arma** | +20 | - | +150 | Unidades ofensivas |
| **Elite** | +10 | +10 | +200 | Unidades versáteis |
| **Veterano** | +15 | +15 | Grátis | Após batalhas |

## 🚀 Como Executar

### Executar demonstração do Decorator
```bash
java src/patterns/decorator/Main.java
```

**Nota:** Não é necessário compilar manualmente. O comando `java` com arquivo `.java` compila e executa automaticamente (Java 11+).

## 📤 Saída Esperada

```
--- TROPA BÁSICA ---

╔════════════════════════════════════════╗
║  TROPA: Espadachim                     ║
╠════════════════════════════════════════╣
║  ⚔️  Ataque: 25                        ║
║  🛡️  Defesa: 20                        ║
║  💰 Custo: 60                          ║
╚════════════════════════════════════════╝

--- ADICIONANDO ARMADURA ---

╔════════════════════════════════════════╗
║  TROPA: Espadachim + Armadura Reforçada║
╠════════════════════════════════════════╣
║  ⚔️  Ataque: 25                        ║
║  🛡️  Defesa: 35                        ║
║  💰 Custo: 160                         ║
╚════════════════════════════════════════╝

--- PROMOVENDO A VETERANO ---

╔════════════════════════════════════════╗
║  TROPA: Espadachim + Armadura Reforçada║
║         + Arma Aprimorada + Treinamento║
║         Elite + Status Veterano ⭐     ║
╠════════════════════════════════════════╣
║  ⚔️  Ataque: 70                        ║
║  🛡️  Defesa: 60                        ║
║  💰 Custo: 510                         ║
╚════════════════════════════════════════╝
```

## ✨ Vantagens do Padrão

✅ **Flexibilidade**: Adiciona funcionalidades dinamicamente  
✅ **Combinação livre**: Decoradores podem ser combinados em qualquer ordem  
✅ **Sem explosão de classes**: Evita criar subclasses para cada combinação  
✅ **Open/Closed**: Aberto para extensão, fechado para modificação  
✅ **Single Responsibility**: Cada decorador tem uma única responsabilidade  

## 🎯 Comparação: 4 Upgrades

### ❌ Com Herança
- **Número de classes**: 2⁴ = 16 classes
- **Com 5 upgrades**: 2⁵ = 32 classes
- **Com 6 upgrades**: 2⁶ = 64 classes

### ✅ Com Decorator
- **Número de classes**: 1 (base) + 4 (decoradores) = **5 classes**
- **Com 5 upgrades**: 6 classes
- **Com 6 upgrades**: 7 classes

**Economia de 11 classes!** (com 4 upgrades)  
**Economia de 57 classes!** (com 6 upgrades)

## 📝 Como Adicionar Novo Upgrade

```java
// 1. Criar novo decorador
public class PoisonUpgrade extends TroopDecorator {
    private static final int ATTACK_BONUS = 25;
    private static final int DEFENSE_PENALTY = -5;
    private static final int UPGRADE_COST = 180;
    
    public PoisonUpgrade(TroopComponent troop) {
        super(troop);
    }
    
    @Override
    public int getAttack() {
        return wrappedTroop.getAttack() + ATTACK_BONUS;
    }
    
    @Override
    public int getDefense() {
        return wrappedTroop.getDefense() + DEFENSE_PENALTY;
    }
    
    @Override
    public int getCost() {
        return wrappedTroop.getCost() + UPGRADE_COST;
    }
    
    @Override
    public String getDescription() {
        return wrappedTroop.getDescription() + " + Veneno 🧪";
    }
}

// 2. Usar
TroopComponent troop = new BasicTroop("Assassino", 30, 15, 80);
troop = new PoisonUpgrade(troop);
troop = new WeaponUpgrade(troop);
// Assassino + Veneno 🧪 + Arma Aprimorada
// Ataque: 75, Defesa: 10, Custo: 410
```

**Nenhuma modificação** no código existente foi necessária! ✨

## 🎮 Exemplo Prático: Evolução de Unidade

```java
// Recruta básico
TroopComponent soldier = new BasicTroop("Soldado", 20, 15, 50);

// Após 1 batalha - Ganhou armadura
soldier = new ArmorUpgrade(soldier);

// Após 3 batalhas - Ganhou arma melhor
soldier = new WeaponUpgrade(soldier);

// Após 5 batalhas - Virou elite
soldier = new EliteTraining(soldier);

// Após 10 batalhas - Virou veterano
soldier = new VeteranStatus(soldier);

// Resultado final
System.out.println(soldier.getDescription());
// "Soldado + Armadura Reforçada + Arma Aprimorada 
//  + Treinamento Elite + Status Veterano ⭐"
System.out.println("Ataque: " + soldier.getAttack());  // 65
System.out.println("Defesa: " + soldier.getDefense()); // 50
System.out.println("Custo: " + soldier.getCost());     // 500
```

## 📚 Arquivos do Padrão

```
src/main/java/patterns/decorator/
├── TroopComponent.java          # Interface componente
├── BasicTroop.java              # Componente concreto
├── TroopDecorator.java          # Decorador abstrato
├── Main.java           # Demonstração do padrão
└── upgrades/
    ├── ArmorUpgrade.java        # Decorador concreto
    ├── WeaponUpgrade.java       # Decorador concreto
    ├── EliteTraining.java       # Decorador concreto
    └── VeteranStatus.java       # Decorador concreto
```

## 🆚 Decorator vs Herança

| Aspecto | Herança | Decorator |
|---------|---------|-----------|
| **Flexibilidade** | Estática (compile-time) | Dinâmica (runtime) |
| **Combinações** | Explosão de classes | Combinações livres |
| **Ordem** | Fixa | Qualquer ordem |
| **Modificação** | Difícil | Fácil |

## 🔗 Referências

- [Refactoring Guru - Decorator](https://refactoring.guru/pt-br/design-patterns/decorator)
- [Refactoring Guru - Design Patterns](https://refactoring.guru/pt-br/design-patterns)