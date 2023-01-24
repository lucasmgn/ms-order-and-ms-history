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

<h2>Postman</h2>
