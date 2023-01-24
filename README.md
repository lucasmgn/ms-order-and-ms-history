# ms-order and ms-history

<h2> Informações</h2>

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

![](./docs/listar-order.png)

<h3>Buscar</h3>
<p>Caso o ID não exista, retornará 404 not found</p>

![](./docs/buscar-id.png)

<h3>Deletar</h3>
<p>Caso o ID não exista, retornará 404 not found como na imagem abaixo, se existir retornará 204 no content</p>

![](./docs/delete-order.png)

<h3>Patch - items</h3>
<p>Caso algum campo for violado, retornará uma mensagem de erro, informando o campo que está inválido e se o ID não existir retornará 404 com uma mensagem de erro</p>
<p>Esse endpoint nos permite alterar um ou vários atributos do objeto Item</p>

![](./docs/patch-response.png)

<h3>Atualizar</h3>
<p>Caso algum campo for violado, retornará uma mensagem de erro, informando o campo que está inválido e se o ID não existir retornará 404 com uma mensagem de erro</p>
<p>Esse endpoint nos permite alterar apenas cpf, total e o objeto address</p>

![](./docs/atualizar-order.png)

<h3>Criar</h3>
<p>Caso algum campo for violado, retornará uma mensagem de erro, informando o campo que está inválido</p>
<p>Para esse endpoint é preciso está com o Kafka rodando no docker e subir também o ms-history</p>
<p>A data estará como null porque ela é preenchida quando é salva no mongdb</p>

![](./docs/docker-kafka.png)
![](./docs/create-order.png)
![](./docs/log-hisstory.png)