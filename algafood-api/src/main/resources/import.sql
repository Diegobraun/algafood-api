insert into cozinha (nome) values ('tailandesa');
insert into cozinha (nome) values ('indiana');
insert into cozinha (nome) values ('holandesa');

insert into restaurante (nome,taxa_frete,cozinha_id) values ('são paulo e cia',15,1);
insert into restaurante (nome,taxa_frete,cozinha_id) values ('grand palace',17,2);
insert into restaurante (nome,taxa_frete,cozinha_id) values ('atiradores',20,3);

insert into forma_pagamento (descricao) values ('cartão de crédito');
insert into forma_pagamento (descricao) values ('cartão de débito');
insert into forma_pagamento (descricao) values ('boleto');

insert into permissao (nome, descricao) values ('de usuário','permissão utilizada para liberar acessos para users');

insert into estado (nome) values ('Rio grande do sul');
insert into estado (nome) values ('Santa Catarina');

insert into cidade (nome, estado_id) values ('Dois irmãos',1);
insert into cidade (nome, estado_id) values ('Novo hamburgo', 1);
insert into cidade (nome, estado_id) values ('Florianópolis',2);