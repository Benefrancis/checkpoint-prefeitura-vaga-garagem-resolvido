# Checkpoint-prefeitura-vaga-garagem-antes
Este � o CheckPoint aplicado para a Turma de DDD -  JAVA do Curso de Tecnologia em Analise e Desenvolvimento de Distemas da FIAP.

# Estudo de caso

O prefeito e toda a administra��o da fict�cia cidade de Fiaplandia, ficaram muito felizes com o programa que voc�, em conjunto com toda equipe de TI da prefeitura, desenvolveu para sorteio de vagas de garagem (nosso simulado). Ap�s pesquisa interna, o �ndice de satisfa��o entre os empregados da prefeitura aumentou muito.


Entretanto, um fato veio � tona. O setor de qualidade, ap�s auditoria interna, verificou uma fragilidade ao analisar os relat�rios desse sistema. Ficou identificado que o funcion�rio respons�vel pelo sorteio fraudava o programa. Ele realizava diversas vezes o sorteio para uma determinado m�s e ano at� que determinadas pessoas fossem sorteadas. Ap�s a simula��o do sorteio, este empregado entregava o relat�rio para publica��o no mural da prefeitura. Mal sabia ele, o hist�rico ficava registrado na tabela SORTE_VAGA_GARAGEM...


Para a solu��o desta vulnerabilidade, outros controles foram criados, entretanto, para a equipe de desenvolvimento de software, os analistas definiram que: ap�s o usu�rio informar os dados de ANO e M�S, o sistema dever� verificar na tabela SORTE_VAGA_GARAGEM se j� existe sorteio para o ano e m�s informado. Havendo; o programa dever� avisar o usu�rio que j� existe sorteio realizado para o m�s e ano informados.

Caber� ao usu�rio dizer se deseja ([ S ] sim ou [ N ] n�o) realizar um novo sorteio. Desejando novo sorteio; os dados do sorteio anterior dever�o ser apagados da tabela. SORTE_VAGA_GARAGEM. Feito isso, todo fluxo de sorteio seguir� normalmente depois.
Os trabalhos de desenvolvimento foram divididos entre a equipe e voc� ficou respons�vel em implementar o m�todo de verifica��o se j� existe sorteio para o M�S e ANO imputados pelo usu�rio no programa.

# O que dever� ser feito

O programa que voc� recebeu j� � capaz de se conectar ao banco de dados desde que voc� preencha corretamente os par�metros dispon�veis no arquivo application.properties. Basta, portanto, preencher os par�metros nesse arquivo e escolher o driver JDBC correto na pasta lib do projeto para se conectar ao SGBD de sua escolha ou o que esteja dispon�vel.
Lembre-se de:

1�) Baixar o projeto no link que forneceremos abaixo;

2�) O banco de dados n�o sofreu altera��o, mas se voc� n�o tiver ou deseje restaur�-lo, basta rodar o script para cria��o das tabelas no banco de dados (o link ser� fornecido abaixo);

2�) Adicionar ao build path do projeto o driver JDBC correspondente ao SGBD.

Voc� dever� criar, no reposit�rio para persist�ncia dos sorteios realizados (SorteioRepository), mais um m�todo jaSorteado (o anterior n�o deve ser apagado ou alterado) para consultar a tabela SORTE_VAGA_GARAGEM e saber se j� existem sorteios para o ANO e M�s informados pelo usu�rio.

N�o se esque�a de criar coment�rios capazes de gerar o JavaDoc da aplica��o e efetivamente criar os arquivos JavaDoc.

# Modelo de dados (Diagrama de entidade e Relacionamento):

<img src="imagens/mer.png" alt="Modelo de Entidade e Relacionamento">

# Script para cria��o do Banco de dados

Caso n�o tenha as tabelas no seu schema ORACLE, voc� dever� rodar o script para cria��o do banco de dados. Segue o link, basta rodar o script contido na pasta do banco de dados que voc� est� utilizando. O professor poder� ajud�-lo nesta etapa:

[https://github.com/Benefrancis/fiap/tree/main/ADS/DDD/Check%20Point/B](https://github.com/Benefrancis/fiap/tree/main/ADS/DDD/Check%20Point/B)  




# Projeto Java

Voc� dever� baixar o projeto Java para realiza��o do check point. Aten��o: voc� ser� o �ltimo a fazer atualiza��es nele. Isto quer dizer que todas as outras funcionalidades j� est�o implementadas e funcionando.

[https://github.com/Benefrancis/fiap/tree/main/ADS/DDD/Check%20Point/B/projeto](https://github.com/Benefrancis/fiap/tree/main/ADS/DDD/Check%20Point/B/projeto)  


# Crit�rio para corre��o e pontua��o:

<img src="imagens/criterio.png" alt="Crit�rio Para Corre��o">
