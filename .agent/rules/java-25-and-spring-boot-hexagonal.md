---
trigger: always_on
---

## 🏗️ Regras de Arquitetura: Hexagonal, Java 25 & Spring Boot

### 1. Estrutura de Camadas e Direção de Dependência

* **Domain (O Core):** Contém Entidades, Objetos de Valor e as interfaces dos *Output Ports* (Repositories).
* **Regra de Ouro:** Dependência ZERO de frameworks (sem anotações do Spring, JPA ou Jackson). Use **Java Records** e **Sealed Interfaces** (recursos fortes no Java 25) para modelar o domínio.


* **Application (Use Cases):** Contém os *Input Ports* e a implementação dos casos de uso. Orquestra o domínio.
* **Infrastructure (Adapters):** Contém a implementação dos *Output Ports* (Persistência, APIs externas) e os *Input Adapters* (Controllers, Consumers). O Spring Boot vive aqui.

### 2. Padrões de Nomenclatura (Naming Convention)

Para garantir clareza absoluta, siga este padrão rigoroso:

| Camada | Sufixo/Padrão | Exemplo |
| --- | --- | --- |
| **Domain** | Nome Simples (Entidade/VO) | `Order`, `Address` |
| **Input Port** | `UseCase` (Interface) | `CreateOrderUseCase` |
| **Application** | `Service` (Implementação) | `OrderService` |
| **Output Port** | `Repository` (Interface) | `OrderRepository` |
| **Input Adapter** | `Controller` / `Consumer` | `OrderController` |
| **Output Adapter** | `Adapter` / `JpaRepository` | `OrderPersistenceAdapter` |
| **DTOs** | `Request` / `Response` | `OrderRequest` |

### 3. Java 25 - Modernização de Código

* **Imutabilidade:** Utilize `record` para todos os DTOs e Objetos de Valor do Domínio.
* **Pattern Matching:** Utilize `switch` expressions e pattern matching para fluxos de decisão baseados em tipos de erro ou estados.
* **Variaveis Locais:** Use `var` para reduzir o ruído visual em implementações de adaptadores.

### 4. Automatização com ArchUnit

O agente deve sugerir ou validar testes de arquitetura que impeçam a violação das camadas. A regra mental para o agente é:

* "Camada de **Domínio** não pode depender de **Application** ou **Infrastructure**."
* "Camada de **Application** não pode depender de **Infrastructure**."
* "Apenas a **Infrastructure** conhece os detalhes de implementação (Spring, Hibernate, etc)."

> **Exemplo de Regra Interna para o Agente:**
> *"Ao criar um novo adaptador de persistência, certifique-se de que ele implementa uma interface definida no Domínio e utiliza um Mapper para converter a Entidade de Domínio em uma Entity do JPA (e vice-versa), nunca expondo a Entity do JPA para fora do adaptador."*