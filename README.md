# Navegação em Grafos - O Retorno dos Fenícios

Este projeto foi desenvolvido para a disciplina de Algoritmos e Estruturas de Dados II e consiste em resolver um problema de otimização de rotas para navegadores fenícios, calculando o menor caminho para visitar uma série de portos e retornar à origem.

## 📝 Descrição do Problema

A tarefa é calcular a quantidade mínima de combustível necessária para uma viagem que começa no porto "1", passa por todos os outros portos em ordem crescente (2, 3, ..., 9) e, finalmente, retorna ao porto "1".

O ambiente de navegação é representado por um mapa em formato de grade, onde:
- `.` representa água navegável.
- `*` representa obstáculos (terra, rochedos, etc.).
- `1` a `9` marcam a localização dos portos.

As regras de navegação são:
- O movimento é restrito às direções Norte, Sul, Leste e Oeste.
- Cada movimento consome uma unidade de combustível.
- Se um porto for inacessível, ele deve ser ignorado, e a rota deve seguir para o próximo porto da sequência.

## 🛠️ Modelagem e Solução

Para solucionar o problema, o mapa foi modelado como um **grafo não ponderado e não dirigido**.

- **Vértices**: Cada célula navegável (`.`, `1`-`9`) no mapa corresponde a um vértice no grafo.
- **Arestas**: Uma aresta é criada entre dois vértices se as células correspondentes no mapa forem adjacentes (acima, abaixo, à esquerda ou à direita).
- **Estruturas de Dados**: Uma **fila de prioridade mínima** foi usada para garantir que os portos fossem visitados na ordem numérica correta (1, 2, 3, ...).

O cálculo da menor distância entre os portos foi realizado utilizando o algoritmo de **Busca em Largura (BFS, ou *Breadth-First Search*)**. O BFS é executado iterativamente para encontrar o caminho mínimo entre cada par de portos consecutivos na rota (do porto `N` para o `N+1`). A distância total é a soma das distâncias de cada trecho, incluindo o retorno final ao porto "1".

A complexidade final do algoritmo é **linear (O(N))**, onde N é o número total de células do mapa, garantindo uma solução eficiente mesmo para mapas muito grandes.

## 🚀 Funcionalidades

- Leitura de mapas de qualquer dimensão a partir de arquivos de entrada.
- Construção dinâmica de um grafo para representar as áreas navegáveis.
- Cálculo da rota ótima visitando os portos em ordem crescente.
- Tratamento de casos onde portos são inacessíveis.
- Acumulação e exibição do custo total de combustível da viagem.

## 📊 Resultados

O algoritmo foi testado com mapas de diferentes tamanhos, e os resultados confirmam sua eficiência e escalabilidade. A tabela abaixo, extraída do relatório de análise, mostra o tempo de execução e o combustível gasto para cada mapa de teste:

| Mapa  | Dimensões     | Elementos   | Tempo Médio (s) | Desvio Padrão (s) | Combustível Gasto |
| :---- | :------------ | :---------- | :-------------- | :---------------- | :---------------- |
| Map 0 | 27 x 50       | 1,350       | 0.00416         | 0.00720           | 248               |
| Map 1 | 55 x 100      | 5,500       | 0.00533         | 0.00479           | 598               |
| Map 2 | 111 x 200     | 22,200      | 0.01993         | 0.01351           | 1112              |
| Map 3 | 277 x 500     | 138,500     | 0.11533         | 0.02668           | 2210              |
| Map 4 | 555 x 1000    | 555,000     | 0.50382         | 0.09981           | 3510              |
| Map 5 | 1111 x 2000   | 2,222,000   | 2.72324         | 0.31556           | 11518             |

O crescimento linear do tempo de execução em relação ao número de elementos pode ser visualizado no gráfico log-log abaixo, o que comprova a complexidade O(N) do algoritmo.

## 🧑‍💻 Autor

- **Henrique P. S. Collatto** - [henrique.017@edu.pucrs.br](mailto:henrique.017@edu.pucrs.br)
