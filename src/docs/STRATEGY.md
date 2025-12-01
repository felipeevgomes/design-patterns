# Strategy Pattern (Padrão Comportamental)

## 📚 Referência
Este padrão é baseado no catálogo do [Refactoring Guru - Strategy](https://refactoring.guru/pt-br/design-patterns/strategy)

## 🎯 Propósito
O Strategy é um padrão de projeto comportamental que permite definir uma família de algoritmos, colocar cada um deles em uma classe separada e tornar seus objetos intercambiáveis.

## 🤖 LLM Utilizada
**Claude (Anthropic) - Sonnet 4.5** foi utilizada na implementação dos exemplos e na estruturação do código.
<br>
**Google Gemini - 3 PRO** foi utilizado para geração de boilerplate de código Java, elaboração de cenários "Age of Empires" e estruturação da documentação Markdown.

## ❓ Problema
No contexto do Age of Empires, civilizações precisam de diferentes estratégias de combate (agressiva, defensiva, balanceada) que podem mudar conforme a situação do jogo:

- **Fase inicial**: Estratégia balanceada
- **Rush**: Estratégia agressiva
- **Sob ataque**: Estratégia defensiva
- **Jogo final**: Estratégia balanceada

**Exemplo do problema sem o padrão:**
```java
// Código ruim - condicionais complexos
public void attack() {
    if (strategy.equals("aggressive")) {
        int power = militaryPower * 1.5;
        defense = defense * 0.7;
        // ...
    } else if (strategy.equals("defensive")) {
        int power = militaryPower * 0.7;
        defense = defense * 1.5;
        // ...
    } else if (strategy.equals("balanced")) {
        // ...
    }
    // Adicionar nova estratégia = modificar este método!
}
```

**Problemas:**
- ❌ Código difícil de manter
- ❌ Violação do Open/Closed Principle
- ❌ Lógica de estratégia misturada com lógica da civilização
- ❌ Difícil testar cada estratégia isoladamente

## ✅ Solução
O padrão Strategy resolve isso através de:

1. **Interface Strategy** (`AttackStrategy`) que define o contrato
2. **Estratégias Concretas** (Aggressive, Defensive, Balanced) implementam a interface
3. **Contexto** (`Civilization`) mantém referência a uma estratégia
4. **Troca dinâmica** de estratégia em tempo de execução

## 📊 Diagrama UML

```
┌──────────────────────────┐          ┌─────────────────────┐
│      Civilization        │          │  AttackStrategy     │ (Interface)
│      (Context)           │◆────────►├─────────────────────┤
├──────────────────────────┤          │+ execute()          │
│- name: String            │          │+ getAttackModifier()│
│- militaryPower: int      │          │+ getDefenseModifier()│
│- resources: int          │          │+ getName()          │
│- strategy: AttackStrategy│          └──────────┬──────────┘
├──────────────────────────┤                     △
│+ setStrategy()           │                     │
│+ attack()                │          ┌──────────┼──────────────────┐
│+ displayInfo()           │          │          │                  │
│+ addResources()          │  ┌───────▼──────┐ ┌▼─────────────┐ ┌──▼──────────┐
└──────────────────────────┘  │ Aggressive   │ │ Defensive    │ │ Balanced    │
                              │ Strategy     │ │ Strategy     │ │ Strategy    │
                              ├──────────────┤ ├──────────────┤ ├─────────────┤
                              │+execute()    │ │+execute()    │ │+execute()   │
                              │+getAttack    │ │+getAttack    │ │+getAttack   │
                              │ Modifier():  │ │ Modifier():  │ │ Modifier(): │
                              │ 1.5          │ │ 0.7          │ │ 1.0         │
                              │+getDefense   │ │+getDefense   │ │+getDefense  │
                              │ Modifier():  │ │ Modifier():  │ │ Modifier(): │
                              │ 0.7          │ │ 1.5          │ │ 1.0         │
                              └──────────────┘ └──────────────┘ └─────────────┘
```

### Explicação do Diagrama:
- **Civilization (Contexto)**: Mantém referência a uma estratégia e delega o comportamento
- **AttackStrategy (Interface)**: Define o contrato que todas as estratégias devem seguir
- **Estratégias Concretas**: Implementam diferentes algoritmos de ataque
- **Relacionamento**: Composição (◆) - Civilization "tem uma" estratégia

## 💻 Estrutura do Código

### 1. Interface Strategy (`AttackStrategy.java`)
```java
public interface AttackStrategy {
    String execute(int basePower);
    double getAttackModifier();
    double getDefenseModifier();
    String getName();
}
```
**Explicação**: Define o contrato para todas as estratégias. Cada estratégia implementa como calcular ataque/defesa.

### 2. Estratégias Concretas

#### Estratégia Agressiva (`AggressiveStrategy.java`)
```java
public class AggressiveStrategy implements AttackStrategy {
    @Override
    public String execute(int basePower) {
        int attackPower = (int)(basePower * getAttackModifier());
        return String.format("⚔️ Ataque AGRESSIVO com poder %d!", attackPower);
    }
    
    @Override
    public double getAttackModifier() {
        return 1.5; // +50% de ataque
    }
    
    @Override
    public double getDefenseModifier() {
        return 0.7; // -30% de defesa
    }
}
```
**Explicação**: Maximiza ataque sacrificando defesa. Ideal para rush offensivo.

#### Estratégia Defensiva (`DefensiveStrategy.java`)
```java
public class DefensiveStrategy implements AttackStrategy {
    @Override
    public double getAttackModifier() {
        return 0.7; // -30% de ataque
    }
    
    @Override
    public double getDefenseModifier() {
        return 1.5; // +50% de defesa
    }
}
```
**Explicação**: Maximiza defesa sacrificando ataque. Ideal para consolidar território.

#### Estratégia Balanceada (`BalancedStrategy.java`)
```java
public class BalancedStrategy implements AttackStrategy {
    @Override
    public double getAttackModifier() {
        return 1.0; // Ataque normal
    }
    
    @Override
    public double getDefenseModifier() {
        return 1.0; // Defesa normal
    }
}
```
**Explicação**: Equilíbrio entre ataque e defesa. Estratégia padrão.

### 3. Contexto (`Civilization.java`)
```java
public class Civilization {
    private String name;
    private int militaryPower;
    private int resources;
    private AttackStrategy strategy; // Referência à estratégia
    
    public Civilization(String name, int resources) {
        this.name = name;
        this.militaryPower = 100;
        this.resources = resources;
        this.strategy = new BalancedStrategy(); // Estratégia padrão
    }
    
    // MÉTODO CHAVE: Permite trocar estratégia em runtime
    public void setStrategy(AttackStrategy strategy) {
        this.strategy = strategy;
        System.out.println("🔄 Mudou para: " + strategy.getName());
    }
    
    // Delega comportamento para a estratégia
    public void attack() {
        if (resources < 50) {
            System.out.println("❌ Recursos insuficientes!");
            return;
        }
        resources -= 50;
        String result = strategy.execute(militaryPower);
        System.out.println("🏛️ " + name + " → " + result);
    }
}
```
**Explicação**: 
- Mantém referência a uma estratégia
- `setStrategy()` permite trocar estratégia dinamicamente
- `attack()` delega o comportamento para a estratégia atual

### 4. Uso do Padrão
```java
Civilization romans = new Civilization("Romanos", 1000);

// Início do jogo - Estratégia balanceada (padrão)
romans.attack(); // Ataque balanceado

// Rush offensivo - Muda para agressiva
romans.setStrategy(new AggressiveStrategy());
romans.attack(); // +50% ataque, -30% defesa

// Sob ataque - Muda para defensiva
romans.setStrategy(new DefensiveStrategy());
romans.attack(); // -30% ataque, +50% defesa

// Jogo final - Volta para balanceada
romans.setStrategy(new BalancedStrategy());
romans.attack(); // Ataque e defesa normais
```

## 📊 Comparação de Estratégias

| Estratégia | Modificador Ataque | Modificador Defesa | Quando Usar |
|-----------|-------------------|-------------------|-------------|
| **Agressiva** | +50% (1.5x) | -30% (0.7x) | Rush, ataque surpresa |
| **Defensiva** | -30% (0.7x) | +50% (1.5x) | Sob ataque, consolidação |
| **Balanceada** | Normal (1.0x) | Normal (1.0x) | Jogo padrão, versatilidade |

## 🚀 Como Executar

### Executar demonstração do Strategy
```bash
java src/patterns/strategy/Main.java
```

**Nota:** Não é necessário compilar manualmente. O comando `java` com arquivo `.java` compila e executa automaticamente (Java 11+).

## 📤 Saída Esperada

```
╔════════════════════════════════════════╗
║  CIVILIZAÇÃO: Romanos                  ║
╠════════════════════════════════════════╣
║  Poder Militar: 100                    ║
║  Recursos: 500                         ║
║  Estratégia: Balanceada                ║
║  Modificador Ataque: 100%              ║
║  Modificador Defesa: 100%              ║
╚════════════════════════════════════════╝

--- Fase 1: Início do jogo (Estratégia Balanceada) ---
🏛️ Romanos → ⚖️ Ataque BALANCEADO com poder 100!
   Recursos restantes: 450

--- Fase 2: Rush offensivo (Estratégia Agressiva) ---
🔄 Romanos mudou para estratégia: Agressiva
💰 Romanos ganhou 100 recursos!
🏛️ Romanos → ⚔️ Ataque AGRESSIVO com poder 150!
   Recursos restantes: 500

--- Fase 3: Consolidando território (Estratégia Defensiva) ---
🔄 Romanos mudou para estratégia: Defensiva
💰 Romanos ganhou 100 recursos!
🏛️ Romanos → 🛡️ Ataque DEFENSIVO com poder 70!
   Recursos restantes: 550
```

## ✨ Vantagens do Padrão

✅ **Troca dinâmica**: Algoritmo pode ser alterado em tempo de execução  
✅ **Isolamento**: Cada estratégia está isolada em sua própria classe  
✅ **Open/Closed**: Fácil adicionar novas estratégias sem modificar código existente  
✅ **Eliminação de condicionais**: Substitui if/else por polimorfismo  
✅ **Testabilidade**: Cada estratégia pode ser testada isoladamente  

## 📝 Como Adicionar Nova Estratégia

```java
// 1. Criar nova estratégia
public class BerserkerStrategy implements AttackStrategy {
    @Override
    public String execute(int basePower) {
        int attackPower = (int)(basePower * getAttackModifier());
        return String.format("😈 Ataque BERSERKER com poder %d!", attackPower);
    }
    
    @Override
    public double getAttackModifier() {
        return 2.0; // +100% de ataque
    }
    
    @Override
    public double getDefenseModifier() {
        return 0.3; // -70% de defesa (muito arriscado!)
    }
    
    @Override
    public String getName() {
        return "Berserker";
    }
}

// 2. Usar
civilization.setStrategy(new BerserkerStrategy());
civilization.attack(); // Ataque massivo, mas defesa mínima!
```

**Nenhuma modificação** no código existente foi necessária! ✨

## 🎮 Exemplo de Fluxo de Jogo

```java
Civilization player = new Civilization("Minha Civilização", 2000);

// Minuto 5: Construindo economia
player.setStrategy(new DefensiveStrategy());
player.attack(); // Defesa enquanto constrói

// Minuto 15: Viu adversário fraco
player.setStrategy(new AggressiveStrategy());
player.attack(); // Rush!
player.attack(); // Continua atacando

// Minuto 20: Adversário contra-atacou
player.setStrategy(new DefensiveStrategy());
player.attack(); // Defende território

// Minuto 30: Jogo estabilizou
player.setStrategy(new BalancedStrategy());
player.attack(); // Jogo equilibrado
```

## 📚 Arquivos do Padrão

```
src/main/java/patterns/strategy/
├── AttackStrategy.java           # Interface Strategy
├── Civilization.java             # Contexto
├── Main.java            # Demonstração do padrão
└── strategies/
    ├── AggressiveStrategy.java  # Estratégia concreta
    ├── DefensiveStrategy.java   # Estratégia concreta
    └── BalancedStrategy.java    # Estratégia concreta
```

## 🆚 Antes vs Depois

### ❌ Antes (Sem o padrão)
```java
public void attack() {
    if (strategyType.equals("aggressive")) {
        // 50 linhas de código agressivo
    } else if (strategyType.equals("defensive")) {
        // 50 linhas de código defensivo
    } else {
        // 50 linhas de código balanceado
    }
}
// Total: 150 linhas em um único método!
```

### ✅ Depois (Com o padrão)
```java
public void attack() {
    String result = strategy.execute(militaryPower);
    System.out.println(result);
}
// Total: 3 linhas! Lógica delegada às estratégias
```

## 🔗 Referências

- [Refactoring Guru - Strategy](https://refactoring.guru/pt-br/design-patterns/strategy)
- [Refactoring Guru - Design Patterns](https://refactoring.guru/pt-br/design-patterns)