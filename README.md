# votacao
Projeto para votacao

O projeto esta hospedado no Railway, mas tive problemas na hora de testar a aplicacao no Railway. Dessa forma peco que seja feito o teste no ambiente local.

Para rodar a aplicacao local so precisa de um banco postgres. 
Utilizar o seguinte comando para criar um container docker com as configuracoes necessarias:

docker run --name postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres -p 5432:5432 -d postgres:14
