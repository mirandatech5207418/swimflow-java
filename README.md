# SwimFlow — Java Backend (MVP)

Projeto didático de portfólio para desenvolvimento Java júnior. Implementa a primeira etapa das regras RN01–RN04 do documento SwimFlow v2.0. **Ainda não é uma solução de produção.**

## Stack
Java 21, Spring Boot 3.4.4, Spring Web, Spring Data JPA, Bean Validation, H2 (execução rápida), PostgreSQL (perfil opcional), Swagger e JUnit/Mockito.

## Executar
1. Instale JDK 21 e Maven 3.9+.
2. Na pasta do projeto, execute `mvn spring-boot:run`.
3. Abra `http://localhost:8080/swagger-ui.html`.
4. Execute os testes com `mvn test`.

### PostgreSQL opcional
Execute `docker compose up -d` e depois `mvn spring-boot:run -Dspring-boot.run.profiles=postgres`.

## Exemplo passo a passo
1. `POST /api/items` com `{"name":"100m crawl","type":"EXERCISE","distanceMeters":100,"status":"ACTIVE"}`.
2. `POST /api/items` com `{"name":"Treino iniciante","type":"WORKOUT","status":"ACTIVE"}`.
3. `POST /api/items/2/components/1` (substitua pelos IDs retornados).
4. `GET /api/items/2/availability` deve retornar `available: true`.
5. `POST /api/sessions` com `{"workoutId":2,"instructor":"Instrutor teste","startsAt":"2027-01-15T10:00:00","capacity":2}` (use uma data futura).
6. `POST /api/sessions/1/enrollments` com `{"swimmerEmail":"nadador@example.com"}`.

## Regras implementadas
- RN01: cadastro de exercício e validação de distância.
- RN02: CHOICE (algum filho ativo), WORKOUT (todos ativos), proibição de composição circular, conjunto vazio indisponível.
- RN03: sessão futura, capacidade positiva, treino disponível.
- RN04: inscrição idempotente por e-mail e sessão, contagem de confirmadas, bloqueio pessimista na sessão para evitar superlotação, cancelamento repetível.

## Limitações e pendências importantes
- **Sem autenticação/autorização**: o endpoint de cancelamento não verifica titularidade; NÃO publicar publicamente.
- RN05 (lista de espera), RN07 (segurança e auditoria), RN08 (agente de IA) não implementadas.
- RN06 apenas parcialmente implementada por idempotência de inscrição/cancelamento.
- H2 é volátil. Perfil PostgreSQL usa `ddl-auto=update` apenas para estudo; antes de produção, introduzir Flyway.
- DTOs de saída e paginação ainda não implementados; entidades JPA são expostas diretamente para reduzir a curva inicial.
- Não foram feitos testes reais de concorrência, integração PostgreSQL, Docker, AWS ou Copilot Studio.
- O Maven não estava disponível no ambiente de geração, portanto compilação/testes precisam ser executados localmente antes de considerar o MVP validado.

## Roteiro de engenharia reversa
1. `SwimFlowApplication` — o que inicia a aplicação?
2. `ItemController` — como uma chamada HTTP chega ao Java?
3. `Requests.Item` — para que servem records e validações?
4. `ItemService.create` — identifique if, enum, parâmetros, retorno e exceções.
5. `ItemService.availableItem` — explique recursão, switch, stream, anyMatch/allMatch.
6. `SwimItem` — identifique classe, atributos, construtor e relacionamentos.
7. `SessionService.enroll` — siga a transação e explique como impede duas vagas para uma capacidade de uma.
8. Execute os testes e altere uma regra por conta própria.

## Próximos incrementos
Segurança com Spring Security, DTOs de saída, testes de integração e concorrência, migrations, lista de espera, auditoria, GitHub Actions, Docker para API e deploy AWS. Só depois integração Microsoft.
