

select * from estado
select * from cidade
select * from endereco




SELECT
    cli.id AS Id,
    cli.nome AS Cliente,
    cli.endereco_logradouro AS LOGRADOURO,
    cli.endereco_numero AS NUMERO,
    cli.endereco_cep AS CEP,
    c.nome AS Cidade,
    uf.nome As UF
FROM
    Cliente cli
    JOIN cidade c ON cli.endereco_cidade_id  = c.id
    JOIN estado uf ON c.estado_id  = uf.id
   WHERE
	cli.id = 1;








-- Vendedor Agencia
SELECT
    cli.id AS Id,
    cli.nome AS Cliente,
    cli.endereco_logradouro AS 'Rua/Avenida',
    cli.endereco_numero AS Numero,
    cli.endereco_cep AS CEP,
    c.nome AS Cidade,
    uf.nome As UF
FROM
    cliente cli
    JOIN cidade c ON v.endereco_cidade_id  = c.id
    JOIN estado uf ON c.estado_id  = uf.id
WHERE
	v.id = 1;