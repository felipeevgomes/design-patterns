# Age of Empires - Design Patterns Implementation

![Java](https://img.shields.io/badge/Java-17+-orange.svg)
![Design Patterns](https://img.shields.io/badge/Design%20Patterns-3-blue.svg)

## 📋 Sobre o Projeto

Este projeto implementa **três padrões de projeto (Design Patterns)** clássicos em Java, utilizando como contexto um sistema inspirado no jogo **Age of Empires**. O objetivo é demonstrar como esses padrões resolvem problemas reais de desenvolvimento de software de forma elegante e manutenível.

### 🎓 Trabalho Acadêmico
Este repositório foi desenvolvido como trabalho acadêmico para demonstrar a aplicação prática de padrões de projeto em sistemas de software.

### 🤖 LLM Utilizada
**Claude (Anthropic) - Sonnet 4.5** foi utilizada na implementação dos exemplos e na estruturação do código.
<br>
**Google Gemini - 3 PRO** foi utilizado para geração de boilerplate de código Java, elaboração de cenários "Age of Empires" e estruturação da documentação Markdown.

### 📚 Referências
Todo o conteúdo teórico sobre os padrões de projeto é baseado no catálogo do [**Refactoring Guru**](https://refactoring.guru/pt-br/design-patterns), respeitando os direitos autorais do material original.

## 🎯 Padrões Implementados

Este projeto implementa **um padrão de cada categoria**:

### 1. 🏭 Factory Method (Criacional)
**Propósito:** Criação de diferentes tipos de tropas para cada civilização

**Problema que resolve:** Como criar objetos sem acoplar o código às classes concretas?

**Solução:** Define uma interface para criar objetos, mas deixa as subclasses decidirem qual classe instanciar.

📖 [Documentação completa do Factory Method](docs/FACTORY_METHOD.md)

**Referência:** [Refactoring Guru - Factory Method](https://refactoring.guru/pt-br/design-patterns/factory-method)

---

### 2. ⚔️ Strategy (Comportamental)
**Propósito:** Diferentes estratégias de combate para civilizações

**Problema que resolve:** Como permitir que o comportamento de um objeto varie sem usar múltiplos condicionais?

**Solução:** Define uma família de algoritmos, encapsula cada um deles e os torna intercambiáveis.

📖 [Documentação completa do Strategy](docs/STRATEGY.md)

**Referência:** [Refactoring Guru - Strategy](https://refactoring.guru/pt-br/design-patterns/strategy)

---

### 3. 🛡️ Decorator (Estrutural)
**Propósito:** Adicionar melhorias e upgrades às tropas dinamicamente

**Problema que resolve:** Como adicionar funcionalidades a objetos sem criar uma explosão de subclasses?

**Solução:** Anexa responsabilidades adicionais a um objeto dinamicamente, fornecendo alternativa flexível à herança.

📖 [Documentação completa do Decorator](docs/DECORATOR.md)

**Referência:** [Refactoring Guru - Decorator](https://refactoring.guru/pt-br/design-patterns/decorator)

## 🎮 Contexto: Age of Empires

O sistema simula elementos de um jogo de estratégia em tempo real:

- **Civilizações** diferentes (Britânicos, Franceses) com características únicas
- **Tropas** (Arqueiros, Espadachins, Cavaleiros) com atributos variados
- **Estratégias de combate** (Agressiva, Defensiva, Balanceada)
- **Upgrades** (Armadura, Arma, Treinamento Elite, Status Veterano)
- **Recursos** necessários para treinar unidades e atacar

## 🏗️ Arquitetura do Projeto

```
design-patterns/
│
├── README.md                          # Este arquivo
│
├── docs/                              # Documentação detalhada
│   ├── FACTORY_METHOD.md             # Guia completo do Factory Method
│   ├── STRATEGY.md                   # Guia completo do Strategy
│   └── DECORATOR.md                  # Guia completo do Decorator
│
│
├── src/
│   ├── Main.java                     # Arquivo principal integrado
│   │
│   └── patterns/
│       │
│       ├── factory/                   # 🏭 Factory Method Pattern
│       │   ├── Troop.java
│       │   ├── TroopFactory.java
│       │   ├── Main.java
│       │   ├── troops/
│       │   │   ├── Archer.java
│       │   │   ├── Swordsman.java
│       │   │   └── Knight.java
│       │   └── civilizations/
│       │       ├── BritishFactory.java
│       │       └── FrenchFactory.java
│       │
│       ├── strategy/                  # ⚔️ Strategy Pattern
│       │   ├── AttackStrategy.java
│       │   ├── Civilization.java
│       │   ├── Main.java
│       │   └── strategies/
│       │       ├── AggressiveStrategy.java
│       │       ├── DefensiveStrategy.java
│       │       └── BalancedStrategy.java
│       │
│       └── decorator/                 # 🛡️ Decorator Pattern
│           ├── TroopComponent.java
│           ├── BasicTroop.java
│           ├── TroopDecorator.java
│           ├── Main.java
│           └── upgrades/
│               ├── ArmorUpgrade.java
│               ├── WeaponUpgrade.java
│               ├── EliteTraining.java
│               └── VeteranStatus.java
│
└── bin/                              # Arquivos 
```

## 🚀 Como Executar o Projeto

### Pré-requisitos
- **Java 11** ou superior instalado
- JDK configurado no PATH

### 1️⃣ Clonar o Repositório
```bash
git clone https://github.com/seu-usuario/design-patterns.git
cd design-patterns
```

### 2️⃣ Executar os Padrões

**Não é necessário compilar manualmente!** O comando `java` com arquivo `.java` compila e executa automaticamente (Java 11+).

#### Executar padrões individualmente
```bash
# Factory Method
java src/patterns/factory/Main.java

# Strategy
java src/patterns/strategy/Main.java

# Decorator
java src/patterns/decorator/Main.java
```

#### Executar arquivo principal integrado
```bash
# Demonstração completa (todos os padrões integrados)
java src/Main.java

# Ou executar padrão específico
java src/Main.java factory
java src/Main.java strategy
java src/Main.java decorator
java src/Main.java all
```

## 📊 Diagramas UML

### Factory Method Pattern
```
                    ┌─────────────────┐
                    │  TroopFactory   │ (Abstract)
                    ├─────────────────┤
                    │+ createArcher() │
                    │+ createSwordsman()
                    │+ createKnight() │
                    └────────┬────────┘
                             △
                  ┌──────────┴──────────┐
                  │                     │
         ┌────────▼─────────┐  ┌───────▼────────┐
         │ BritishFactory   │  │ FrenchFactory  │
         └──────────────────┘  └────────────────┘
```
[Ver diagrama completo](diagrams/factory_uml.png)

### Strategy Pattern
```
┌──────────────────┐          ┌─────────────────┐
│   Civilization   │◆────────►│ AttackStrategy  │
│   (Context)      │          │  (Interface)    │
└──────────────────┘          └────────┬────────┘
                                       △
                              ┌────────┼────────┐
                              │        │        │
                        Aggressive Defensive Balanced
```
[Ver diagrama completo](diagrams/strategy_uml.png)

### Decorator Pattern
```
        ┌────────────────┐
        │ TroopComponent │ (Interface)
        └───────┬────────┘
                △
        ┌───────┴────────────┐
        │                    │
  ┌─────▼─────┐    ┌────────▼────────┐
  │BasicTroop │    │ TroopDecorator  │
  └───────────┘    └────────┬─────────┘
                            △
                   ┌────────┼────────┐
                   │        │        │
              ArmorUpg  WeaponUpg EliteTraining
```
[Ver diagrama completo](diagrams/decorator_uml.png)

## 💡 Exemplos de Uso

### Factory Method - Criando Tropas
```java
TroopFactory britishFactory = new BritishFactory();
Troop archer = britishFactory.createArcher();
archer.display();
// 🏹 Arqueiro Britânico (Ataque: 35, Defesa: 10...)
```

### Strategy - Mudando Táticas
```java
Civilization romans = new Civilization("Romanos", 1000);
romans.setStrategy(new AggressiveStrategy());
romans.attack(); // Ataque com +50% de poder
```

### Decorator - Aplicando Upgrades
```java
TroopComponent troop = new BasicTroop("Espadachim", 25, 20, 60);
troop = new ArmorUpgrade(troop);
troop = new WeaponUpgrade(troop);
troop.display();
// Espadachim + Armadura + Arma (Ataque: 45, Defesa: 35)
```

## 🎯 Demonstração Integrada

O arquivo `Main.java` contém uma demonstração completa que integra os três padrões em uma simulação de partida:

1. **Fase 1 (Factory Method)**: Civilizações criam seus exércitos
2. **Fase 2 (Decorator)**: Tropas recebem upgrades
3. **Fase 3 (Strategy)**: Civilizações escolhem estratégias de combate
4. **Fase 4**: Ajustes táticos durante a partida

```bash
java -cp bin Main all
```

## 📖 Documentação Detalhada

Cada padrão possui sua própria documentação completa:

- 📄 [**Factory Method**](src/docs/FACTORY_METHOD.md) - Problema, Solução, Diagrama UML, Código Explicado
- 📄 [**Strategy**](src/docs/STRATEGY.md) - Problema, Solução, Diagrama UML, Código Explicado
- 📄 [**Decorator**](src/docs/DECORATOR.md) - Problema, Solução, Diagrama UML, Código Explicado

## ✨ Benefícios dos Padrões Implementados

### Factory Method
✅ Desacoplamento entre criação e uso  
✅ Facilita adição de novas civilizações  
✅ Código segue o princípio Open/Closed  

### Strategy
✅ Elimina condicionais complexos  
✅ Troca de comportamento em runtime  
✅ Facilita adição de novas estratégias  

### Decorator
✅ Evita explosão de subclasses  
✅ Combina funcionalidades dinamicamente  
✅ Adiciona responsabilidades sem modificar código  

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java 17+
- **Paradigma:** Orientação a Objetos
- **Padrões:** Design Patterns (GoF)
- **Documentação:** Markdown
- **Controle de Versão:** Git/GitHub

## 📝 Estrutura de Commits

O projeto foi organizado com commits semânticos:

```bash
feat(factory): Add Factory Method pattern implementation
feat(strategy): Add Strategy pattern implementation
feat(decorator): Add Decorator pattern implementation
docs: Add comprehensive documentation for all patterns
feat: Add integrated Main class with all patterns
```

## 🤝 Como Contribuir

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/NovaFuncionalidade`)
3. Commit suas mudanças (`git commit -m 'feat: Adiciona nova funcionalidade'`)
4. Push para a branch (`git push origin feature/NovaFuncionalidade`)
5. Abra um Pull Request


## 👨‍💻 Autor

Desenvolvido como projeto acadêmico para demonstração de Design Patterns.

## 🔗 Referências e Créditos

### Padrões de Projeto
- [Refactoring Guru - Design Patterns](https://refactoring.guru/pt-br/design-patterns) - Catálogo completo
- [Refactoring Guru - Factory Method](https://refactoring.guru/pt-br/design-patterns/factory-method)
- [Refactoring Guru - Strategy](https://refactoring.guru/pt-br/design-patterns/strategy)
- [Refactoring Guru - Decorator](https://refactoring.guru/pt-br/design-patterns/decorator)

### Livros
- "Design Patterns: Elements of Reusable Object-Oriented Software" - Gang of Four (GoF)
- "Head First Design Patterns" - Eric Freeman & Elisabeth Robson

### LLM
- **Claude (Anthropic) - Sonnet 4.5** - Assistência na implementação e documentação
<br>
**Google Gemini - 3 PRO** - Estruturação da documentação Markdown.

---

**Nota sobre Direitos Autorais:** O conteúdo teórico sobre os padrões de projeto apresentado neste repositório é baseado e referencia o material disponível no [Refactoring Guru](https://refactoring.guru/pt-br/design-patterns), respeitando os direitos autorais do conteúdo original. A implementação em código é original e desenvolvida para fins educacionais.

---

⭐ Se este projeto foi útil para você, considere dar uma estrela no repositório!