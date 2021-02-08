# Spring Microservices (Game Ecommerce) [WIP]

## Descrição

O objetivo deste projeto pessoal desenvolvido por mim é único e exclusivamente para fins didáticos. Portanto se algo pode ser melhorado, entre em contato comigo e me dê sugestões. Sem mais delongas, o projeto se trata da implementação de um subconjunto de algumas funcionalidades de um e-commerce de jogos eletrônicos utilizando arquitetura orientada a microsserviços. O ecommerce então será divido em algumas partes:

* **auth-server** : o microserviço responsável pela autenticação dos usuários. Ele é responsável por armazenar algumas informações relacionadas aos usuários, como `username` e `password` , por exemplo. Ele será usado pelos outros microsserviços para autorização (ver observações);
* **user-microservice**: microserviço responsável por gerenciar as informações de usuário.

O sistema como um todo foi desenvolvido usando Kotlin, Postgres como banco de dados relacional, RabbitMQ como mensageiro e Netflix Eureka para descobrimente de microserviços. A finalidade desse projeto é botar em prática os conhecimentos adiquiridos pelo curso "Construindo sua aplicação orientada a microsserviços" da Udemy.

### Obervações

* Autenticação e autorização: autenticação é a capacidade de um sistema suportar login e logout, enquanto que a autorização está relacionada a verificar quais recursos um determinado usuário tem acesso. Por exemplo, um cliente só é capaz de visualizar os detalhes do produto, mas não é capaz de alterá-los.
* Este projeto está em andamento, portanto as funcionalidades estão sendo adicionadas aos poucos.