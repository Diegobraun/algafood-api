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