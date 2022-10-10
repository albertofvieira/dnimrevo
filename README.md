# Overmind Test

### Antes da primeira execução:
- No arquivo application.properties do projeto overmind-api, incluir a propriedade que foi enviada por email


### Executando: 
- No diretório raiz, basta rodar o comando: docker-compose up
* Pode demorar na primeira execução se as imagens relacionadas não existirem localmente

- Ao subir o serviço, um dos testes vai enviar um email para a conta indicada. O mesmo pode ser feito através do formulário de email

### overmind-ui
Angular 14
- http://localhost:4200


### overmind-api
Spring Boot (Java 17):

- Webcrawler: http://localhost:8080/api/v1/crawler/imdb/piores
* Simples GET via browser ou Postman. Pode demorar um pouco o processamento


