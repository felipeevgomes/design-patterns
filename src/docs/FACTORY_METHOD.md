# Factory Method Pattern (Padrão Criacional)

## 📚 Referência
Este padrão é baseado no catálogo do [Refactoring Guru - Factory Method](https://refactoring.guru/pt-br/design-patterns/factory-method)

## 🎯 Propósito
O Factory Method é um padrão de projeto criacional que fornece uma interface para criar objetos em uma superclasse, mas permite que as subclasses alterem o tipo de objetos que serão criados.

## 🤖 LLM Utilizada
**Claude (Anthropic) - Sonnet 4.5** foi utilizada na implementação dos exemplos e na estruturação do código.
<br>
**Google Gemini - 3 PRO** foi utilizado para geração de boilerplate de código Java, elaboração de cenários "Age of Empires" e estruturação da documentação Markdown.

## ❓ Problema
No contexto do Age of Empires, cada civilização precisa criar diferentes tipos de tropas (arqueiros, espadachins, cavaleiros). Sem um padrão adequado:

- O código fica **acoplado** às classes concretas de tropas
- **Difícil** adicionar novas civilizações
- **Difícil** modificar características específicas de cada civilização
- Código cliente precisa conhecer **todas** as classes concretas

**Exemplo do problema:**
```java
// Código ruim - acoplado
if (civilization.equals("British")) {
    archer = new Archer("British", 35, 10, 40, 20); // Bônus britânico
} else if (civilization.equals("French")) {
    archer = new Archer("French", 30, 10, 40, 20); // Sem bônus
}
// Adicionar nova civilização = modificar código existente!
```

## ✅ Solução
O padrão Factory Method resolve isso através de:

1. **Interface comum** (`Troop`) para todos os produtos
2. **Classe abstrata Factory** (`TroopFactory`) com métodos de criação
3. **Fábricas concretas** (`BritishFactory`, `FrenchFactory`) que implementam a criação específica
4. **Desacoplamento** - código cliente trabalha com abstrações

## 📊 Diagrama UML

```
                    ┌─────────────────┐
                    │  TroopFactory   │ (Abstract)
                    ├─────────────────┤
                    │+ createArcher() │◄─────┐
                    │+ createSwordsman()│    │
                    │+ createKnight()  │     │
                    │+ trainArmy()     │     │
                    └────────┬─────────┘     │
                             △               │
                             │               │
                  ┌──────────┴──────────┐    │
                  │                     │    │
         ┌────────▼─────────┐  ┌───────▼────────┐
         │ BritishFactory   │  │ FrenchFactory  │
         ├──────────────────┤  ├────────────────┤
         │+ createArcher()  │  │+ createArcher()│
         │+ createSwordsman()│  │+ createSwordsman()│
         │+ createKnight()  │  │+ createKnight()│
         └──────────────────┘  └────────────────┘
                  │                     │
                  │ creates             │ creates
                  ▼                     ▼
            ┌──────────┐          ┌──────────┐
            │  Troop   │◄─────────│  Troop   │
            └──────────┘          └──────────┘
                  △                     
                  │                     
       ┌──────────┼──────────┐
       │          │          │
  ┌────▼───┐ ┌───▼────┐ ┌───▼────┐
  │ Archer │ │Swordsman│ │ Knight │
  └────────┘ └─────────┘ └────────┘
```

### Explicação do Diagrama:
- **TroopFactory**: Classe abstrata que define os Factory Methods
- **BritishFactory/FrenchFactory**: Implementações concretas que criam tropas específicas
- **Troop**: Interface que todas as tropas implementam
- **Archer/Swordsman/Knight**: Produtos concretos criados pelas fábricas

## 💻 Estrutura do Código

### 1. Interface do Produto (`Troop.java`)
```java
public interface Troop {
    int getAttack();
    int getDefense();
    int getGoldCost();
    int getWoodCost();
    String getDescription();
    void display();
}
```
**Explicação**: Define o contrato que todas as tropas devem seguir.

### 2. Produtos Concretos (`Archer.java`, `Swordsman.java`, `Knight.java`)
```java
public class Archer implements Troop {
    private String civilization;
    private int attack;
    private int defense;
    // ...
    
    public Archer(String civilization, int attack, int defense, 
                  int goldCost, int woodCost) {
        this.civilization = civilization;
        this.attack = attack;
        // ...
    }
}
```
**Explicação**: Implementações concretas da interface Troop.

### 3. Factory Abstrata (`TroopFactory.java`)
```java
public abstract class TroopFactory {
    // Factory Methods - serão implementados pelas subclasses
    public abstract Troop createArcher();
    public abstract Troop createSwordsman();
    public abstract Troop createKnight();
    
    // Método template que usa os factory methods
    public void trainArmy() {
        Troop archer = createArcher();
        Troop swordsman = createSwordsman();
        Troop knight = createKnight();
        // ...
    }
}
```
**Explicação**: Define a interface para criação, mas delega às subclasses a decisão de qual classe instanciar.

### 4. Fábricas Concretas (`BritishFactory.java`)
```java
public class BritishFactory extends TroopFactory {
    @Override
    public Troop createArcher() {
        // Britânicos têm bônus em arqueiros (+5 ataque)
        return new Archer("Britânico", 35, 10, 40, 20);
    }
    
    @Override
    public Troop createSwordsman() {
        return new Swordsman("Britânico", 25, 20, 60, 10);
    }
    
    @Override
    public Troop createKnight() {
        return new Knight("Britânico", 40, 30, 120, 0);
    }
}
```
**Explicação**: Implementa os factory methods criando tropas com características únicas dos britânicos.

### 5. Uso do Padrão
```java
// Cliente trabalha com abstrações, não classes concretas
TroopFactory factory = new BritishFactory();
Troop archer = factory.createArcher(); // Cria arqueiro britânico
archer.display(); // Arqueiro Britânico (Ataque: 35...)

// Fácil trocar de civilização
factory = new FrenchFactory();
archer = factory.createArcher(); // Cria arqueiro francês
archer.display(); // Arqueiro Francês (Ataque: 30...)
```

## 🎮 Características Especiais por Civilização

| Civilização | Especialidade | Bônus |
|------------|---------------|-------|
| **Britânicos** | Arqueiros | +5 de ataque nos arqueiros |
| **Franceses** | Cavaleiros | +10 de ataque e +5 de defesa nos cavaleiros |

## 🚀 Como Executar

### Executar demonstração do Factory Method
```bash
java src/patterns/factory/Main.java
```

**Nota:** Não é necessário compilar manualmente. O comando `java` com arquivo `.java` compila e executa automaticamente (Java 11+).

## 📤 Saída Esperada

```
🏴󠁧󠁢󠁥󠁮󠁧󠁿 CIVILIZAÇÃO BRITÂNICA

=== Treinando Exército ===
🏹 Arqueiro Britânico (Ataque: 35, Defesa: 10, Custo: 40 ouro, 20 madeira)
⚔️ Espadachim Britânico (Ataque: 25, Defesa: 20, Custo: 60 ouro, 10 madeira)
🐴 Cavaleiro Britânico (Ataque: 40, Defesa: 30, Custo: 120 ouro, 0 madeira)

🇫🇷 CIVILIZAÇÃO FRANCESA

=== Treinando Exército ===
🏹 Arqueiro Francês (Ataque: 30, Defesa: 10, Custo: 40 ouro, 20 madeira)
⚔️ Espadachim Francês (Ataque: 25, Defesa: 20, Custo: 60 ouro, 10 madeira)
🐴 Cavaleiro Francês (Ataque: 50, Defesa: 35, Custo: 120 ouro, 0 madeira)

=== Comparando Arqueiros ===
Britânico: 35 ataque
Francês: 30 ataque
```

## ✨ Vantagens do Padrão

✅ **Desacoplamento**: Código cliente não depende de classes concretas  
✅ **Extensibilidade**: Fácil adicionar novas civilizações sem modificar código existente  
✅ **Single Responsibility**: Criação de objetos está centralizada nas factories  
✅ **Open/Closed**: Aberto para extensão, fechado para modificação  

## 📝 Como Adicionar Nova Civilização

```java
// 1. Criar nova fábrica
public class SpanishFactory extends TroopFactory {
    @Override
    public Troop createArcher() {
        return new Archer("Espanhol", 30, 10, 35, 20); // Custo reduzido
    }
    
    @Override
    public Troop createSwordsman() {
        return new Swordsman("Espanhol", 28, 20, 60, 10); // Ataque +3
    }
    
    @Override
    public Troop createKnight() {
        return new Knight("Espanhol", 40, 30, 120, 0);
    }
}

// 2. Usar
TroopFactory spanish = new SpanishFactory();
spanish.trainArmy();
```

**Nenhuma modificação** no código existente foi necessária! ✨

## 📚 Arquivos do Padrão

```
src/main/java/patterns/factory/
├── Troop.java                    # Interface do produto
├── TroopFactory.java             # Factory abstrata
├── Main.java                     # Demonstração do padrão
├── troops/
│   ├── Archer.java              # Produto concreto
│   ├── Swordsman.java           # Produto concreto
│   └── Knight.java              # Produto concreto
└── civilizations/
    ├── BritishFactory.java      # Factory concreta
    └── FrenchFactory.java       # Factory concreta
```

## 🔗 Referências

- [Refactoring Guru - Factory Method](https://refactoring.guru/pt-br/design-patterns/factory-method)
- [Refactoring Guru - Design Patterns](https://refactoring.guru/pt-br/design-patterns)