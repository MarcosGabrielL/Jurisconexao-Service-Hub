# Jurisconexao-Service-Hub

![image](https://user-images.githubusercontent.com/52946597/235253425-f6e42875-d895-47a9-a440-7fc4204d1980.png)

#### padrão gateway
 - Um proxy reverso do sistema distribuído para uso como um modelo de comunicação síncrona, portanto, fornece um único ponto de entrada para as APIs encapsulando a arquitetura do sistema subjacente e mapeia internamente as solicitações para microsserviços internos.

 - Outro benefício é abstrair as operações internas sobre os microsserviços de back-end, portanto, mesmo que haja alterações nos microsserviços de back-end, isso não afetará os aplicativos do cliente. 
 
 - Agregar vários microsserviços internos em uma única solicitação do cliente. Com essa abordagem, o aplicativo cliente envia uma única solicitação ao API Gateway. Depois disso, o API Gateway despacha várias solicitações para os microsserviços internos e, em seguida, agrega os resultados e envia tudo de volta para o aplicativo cliente em uma única resposta
 
 - Ele também fornece preocupações transversais, como Autenticação e autorização, Integração de descoberta de serviço, Cache de resposta, Políticas de repetição, disjuntor e QoS, Limitação de taxa e estrangulamento, Balanceamento de carga, Log, rastreamento, correlação, Cabeçalhos, strings de consulta, transformação de declarações e lista de permissões de IP
 
#### Autenticação e autorização
#### Integração de descoberta de serviço
#### Cache de resposta
#### Políticas de repetição , disjuntor e QoS
#### Limitação de taxa e estrangulamento
#### Balanceamento de carga
#### Log, rastreamento, correlação
#### Cabeçalhos, strings de consulta e transformação de declarações
#### lista de permissões de IP
 
 
