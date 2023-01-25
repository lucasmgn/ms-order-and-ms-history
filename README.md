# ms-order and ms-history

<h2> Informações</h2>
Os microserviços <strong>Order</strong> e <strong>History</strong> podem ser rodados de maneira individual, porém para o endpoint <strong>CRIAR</strong> do <strong>Order</strong> às duas aplicações + o <strong>Kafka</strong> precisam estar "startados". 

Projetos de microserviços Order e History, as tecnologias usadas nesses
projetos foram:
<ul>
  <li>Spring Boot</li>
  <li>Docker</li>
  <li>Kafka</li>
  <li>Model Mapper</li>
  <li>Lombok</li>
  <li>MongoDB</li>
  <li>MYSQL</li>
  <li>Flyway</li>
  <li>Postman</li>
</ul>

<h3> Temos na pasta docker o arquivo docker-compose.yaml para poder rodar o Kafka e fazer a comunicação entre os dois microserviços </h3>

![](./docs/image-arquivo-compose.png)

<h3> A arquitetura de ambos projetos é a hexagonal </h3>

![](./docs/arquitetura-hexagonal.png)

<h2>ms - order</h2>

<h3> O ms-order é o microserviço producer, ele possui classes de serviços de ITEM, ORDER e ADDRESS, incluindo as classes de serialização, nele é utilizado a Api Viacep e possui o TopicProducer</h3>

![](./docs/image-service-and-viacep.png)

<h3>Postman</h3>

<h3>Listar</h3>
<p>Podendo filtrar por cpf e por maior valor (atenção na pesquisa por cpf, ela precisa ser igual ao cadastrado)</p>
<p><strong>cURL:</strong> curl --location --request GET 'http://localhost:8080/api/pedidos/'</p>

![](./docs/listar-order.png)

<h4>Response Completo</h4>

```{
"id": 1,
"cpf": "9591781555",
"items": [
{
"id": 2,
"name": "Meia Compasso Java",
"creation": "26-02-2002",
"expiration": "01-01-2026",
"price": 45.00,
"description": "Par de meias Compass.uol"
}
],
"total": 45.00,
"address": {
"id": 1,
"street": "Rua Jairo Andrade Macedo",
"number": "56",
"neighborhood": "São Conrado",
"city": "Aracaju",
"state": "SE",
"cep": "49042-480"
}
}
````
<h3>Buscar</h3>
<p>Caso o ID não exista, retornará 404 not found</p>
<p><strong>cURL:</strong> curl --location --request GET 'http://localhost:8080/api/pedidos/1'</p>

![](./docs/buscar-id.png)

<h3>Deletar</h3>
<p>Caso o ID não exista, retornará 404 not found como na imagem abaixo, se existir retornará 204 no content</p>
<p><strong>cURL:</strong> curl --location --request DELETE 'http://localhost:8080/api/pedidos/12'</p>

![](./docs/delete-order.png)

<h3>Patch - items</h3>
<p>Caso algum campo for violado, retornará uma mensagem de erro, informando o campo que está inválido e se o ID não existir retornará 404 com uma mensagem de erro</p>
<p>Esse endpoint nos permite alterar um ou vários atributos do objeto Item</p>
<p><strong>cURL:</strong> curl --location --request PATCH 'http://localhost:8080/api/itens/2' \
--header 'Content-Type: application/json' \
--data-raw '
{
    "creation": "26-02-2002"
}'</p>

![](./docs/patch-response.png)

<h3>Atualizar</h3>
<p>Caso algum campo for violado, retornará uma mensagem de erro, informando o campo que está inválido e se o ID não existir retornará 404 com uma mensagem de erro</p>
<p>Esse endpoint nos permite alterar apenas cpf, total e o objeto address</p>
<p><strong>cURL:</strong> curl --location --request PUT 'http://localhost:8080/api/pedidos/2' \
--header 'Content-Type: application/json' \
--data-raw ' {
        "cpf": "095.917.815-55",
        "total": 5663.00,
        "address": {
            "street": "Rua Jairo Andrade Macedo",
            "number": "56",
            "neighborhood": "São Conrado",
            "city": "Aracaju",
            "state": "SE",
            "cep": "49042-480"
        }
    }'</p>

![](./docs/atualizar-order.png)

<h3>Criar</h3>
<p>Caso algum campo for violado, retornará uma mensagem de erro, informando o campo que está inválido</p>
<p>Para esse endpoint é preciso está com o Kafka rodando no docker e subir também o ms-history</p>
<p>A data estará como null porque ela é preenchida quando é salva no mongdb</p>
<p><strong>cURL:</strong> curl --location --request POST 'http://localhost:8080/api/pedidos/' \
--header 'Content-Type: application/json' \
--data-raw ' {
        "cpf": "095.917.815-55",
        "items": [
            {
                "name": "Agua",
                "creation": "03-12-2022",
                "expiration": "03-12-2026",
                "price": 15.00,
                "description": "Par de meias Compass.uol"
            },
            {
                "name": "Agua",
                "creation": "03-12-2022",
                "expiration": "11-02-2026",
                "price": 45.00,
                "description": "Par de meias Compass.uol"
            }
        ],
        "address": {
            "cep": "40430-390",
            "number": "9A"
        }
    }'</p>

![](./docs/docker-kafka.png)
![](./docs/create-order.png)
![](./docs/log-hisstory.png)

<h2>ms - history</h2>

<h3> O ms-history é o microserviço consumer</h3>

![](./docs/topic-consumer-history.png)


<h3>Postman</h3>

<h3>Listar</h3>
<p>Listagem ocorre na ordem cronológica inversa, ou seja, listará do mais recente até o mais antigo, também possui o filtro entre duas datas, trazendo apenas o history das datas entre elas</p>
<p><strong>cURL:</strong> curl --location --request GET 'http://localhost:8085/api/history/'</p>

![](./docs/listar-history.png)

<p>Listando history com filtro de data inicio e fim</p>
<p><strong>cURL:</strong> curl --location --request GET 'http://localhost:8085/api/history/?inicio=2022-12-13&fim=2023-01-24'</p>

![](./docs/ordem-history.png)

<h4>Response</h4>

````
{
"cod": "63c94e69cd8ff26bfb3fe67c",
"idOrder": 26,
"total": 60.00,
"date": "19-01-2023"
},
{
"cod": "63c9fcb187a4923940bd8d14",
"idOrder": 29,
"total": 60.00,
"date": "20-01-2023"
},
{
"cod": "63c9fcba87a4923940bd8d15",
"idOrder": 30,
"total": 60.00,
"date": "19-01-2023"
},
{
"cod": "63cf2ec098bd4932773ece04",
"idOrder": 31,
"total": 60.00,
"date": "23-01-2023"
},
{
"cod": "63cf2ec998bd4932773ece05",
"idOrder": 32,
"total": 60.00,
"date": "23-01-2023"
}
````