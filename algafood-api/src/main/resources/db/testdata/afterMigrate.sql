
set foreign_key_checks = 0;

delete from cidade;
delete from cozinha;
delete from estado;
delete from forma_pagamento;
delete from grupo;
delete from grupo_permissao;
delete from permissao;
delete from produto;
delete from restaurante;
delete from restaurante_forma_pagamento;
delete from usuario;
delete from usuario_grupo;
delete from pedido;
delete from item_pedido;

set foreign_key_checks = 1;

alter table cidade auto_increment=1;
alter table cozinha auto_increment=1;
alter table estado auto_increment=1;
alter table forma_pagamento auto_increment=1;
alter table grupo auto_increment=1;
alter table permissao auto_increment=1;
alter table produto auto_increment=1;
alter table restaurante auto_increment=1;
alter table usuario auto_increment=1;
alter table pedido auto_increment=1;
alter table item_pedido auto_increment=1;

insert into cozinha (nome) values ('tailandesa');
insert into cozinha (nome) values ('indiana');
insert into cozinha (nome) values ('holandesa');

insert into estado (nome) values ('Rio grande do sul');
insert into estado (nome) values ('Santa Catarina');

insert into cidade (nome, estado_id) values ('Dois irmãos',1);
insert into cidade (nome, estado_id) values ('Novo hamburgo', 1);
insert into cidade (nome, estado_id) values ('Florianópolis',2);

insert into restaurante (nome,taxa_frete,cozinha_id, endereco_cidade_id, endereco_cep, endereco_logradouro,endereco_numero,endereco_bairro,data_cadastro,data_atualizacao) values ('são paulo e cia',15,1,1,'38400-999','Rua joão pinheiro','1000','centro',utc_timestamp,utc_timestamp);

insert into restaurante (nome,taxa_frete,cozinha_id,data_cadastro,data_atualizacao) values ('grand palace',17,2,utc_timestamp,utc_timestamp);
insert into restaurante (nome,taxa_frete,cozinha_id,data_cadastro,data_atualizacao) values ('atiradores',20,3,utc_timestamp,utc_timestamp);

insert into forma_pagamento (descricao) values ('cartão de crédito');
insert into forma_pagamento (descricao) values ('cartão de débito');
insert into forma_pagamento (descricao) values ('boleto');

insert into permissao (nome, descricao) values ('de usuário','permissão utilizada para liberar acessos para users');

insert into produto(ativo,descricao,preco,restaurante_id) values (true,"massa caseira",2.39,1);
insert into produto(ativo,descricao,preco,restaurante_id) values (false,"feijão",4.19,1);
insert into produto(ativo,descricao,preco,restaurante_id) values (true,"arroz branco",18,1);
insert into produto(ativo,descricao,preco,restaurante_id) values (true,"massa caseira",2.39,2);
insert into produto(ativo,descricao,preco,restaurante_id) values (false,"feijão",4.19,2);
insert into produto(ativo,descricao,preco,restaurante_id) values (true,"arroz branco",18,2);
insert into produto(ativo,descricao,preco,restaurante_id) values (true,"massa caseira",2.39,3);
insert into produto(ativo,descricao,preco,restaurante_id) values (false,"feijão",4.19,3);
insert into produto(ativo,descricao,preco,restaurante_id) values (true,"arroz branco",18,3);

insert into restaurante_forma_pagamento (restaurante_id,forma_pagamento_id) values (1,1);
insert into restaurante_forma_pagamento (restaurante_id,forma_pagamento_id) values (1,2);
insert into restaurante_forma_pagamento (restaurante_id,forma_pagamento_id) values (1,3);
insert into restaurante_forma_pagamento (restaurante_id,forma_pagamento_id) values (2,3);
insert into restaurante_forma_pagamento (restaurante_id,forma_pagamento_id) values (3,2);
insert into restaurante_forma_pagamento (restaurante_id,forma_pagamento_id) values (3,3);

insert into usuario (data_cadastro,email, nome, senha) values (utc_timestamp,"diegobraun2000@gmail.com","Diego","12345");

insert into pedido(sub_total,taxa_frete,valor_total,data_criacao,endereco_cep,endereco_bairro,endereco_logradouro,
endereco_numero,endereco_cidade_id,status,usuario_cliente_id,restaurante_id,forma_pagamento_id)
values(12,16,30,utc_timestamp,"93950000","união","Rua 29 de setembro","269",1,"CRIADO",1,1,1);
 
insert into item_pedido(quantidade,preco_unitario,preco_total,pedido_id,produto_id) values (2,1.34,18.23,1,1);
 

