# etl-gastos-parlamentares

1) Crie o banco de dados com o nome "gastoparlamentar" no MySQL. Caso queira dar outro nome, o arquivo application.properties armazena dados do nome do banco, usuário e senha.
2) Execute o script de criação do banco que se encontra na pasta resource (script.sql);
3) Baixe os arquivos csv do site https://dadosabertos.camara.leg.br/swagger/api.html#staticfile ;
4) Extraia os arquivos .csv na pasta resources;
5) Abra a classe CargaSimplificadaService e altere a variável pública "arquivos" contendo o nome dos arquivos (ex: public String[] arquivos = {"2019.csv", "2020.csv", "2021.csv"}; )
6) Execute a aplicação.