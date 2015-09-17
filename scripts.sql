CREATE TABLE "Cliente"
(
  cpf bigint NOT NULL, 
  nome character varying(50) NOT NULL, 
  telefone character varying(15) NOT NULL, 
  email character varying(50) NOT NULL, 
  CONSTRAINT "ClientePK" PRIMARY KEY (cpf)
)

CREATE TABLE "Imovel"
(
  identificador bigint NOT NULL, 
  cpf_proprietario bigint NOT NULL, 
  codigo_ibge_estado integer NOT NULL, 
  endereco character varying(100) NOT NULL, 
  descricao character varying(200) NOT NULL, 
  valor_aluguel numeric(9,2) NOT NULL, 
  codigo_ibge_municipio integer NOT NULL, 
  CONSTRAINT "PK_Imovel" PRIMARY KEY (identificador),
  CONSTRAINT "FK_Imovel_Cliente" FOREIGN KEY (cpf_proprietario)
      REFERENCES "Cliente" (cpf) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)

CREATE TABLE "Aluguel"
(
  identificador bigint NOT NULL, 
  data date NOT NULL, 
  cpf_inquilino bigint NOT NULL, 
  identificador_imovel bigint NOT NULL, 
  valor numeric(9,2) NOT NULL, 
  CONSTRAINT "PK_Aluguel" PRIMARY KEY (identificador),
   CONSTRAINT "FK_Aluguel_Cliente" FOREIGN KEY (cpf_inquilino)
      REFERENCES "Cliente" (cpf) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "FK_Aluguel_Imovel" FOREIGN KEY (identificador_imovel)
      REFERENCES "Imovel" (identificador) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)

INSERT INTO "Cliente"(cpf, nome, telefone, email)
    VALUES (12345678909, 'João da Silva', 99998888, 'joao@gmail.com');
    
INSERT INTO "Imovel"(identificador, cpf_proprietario, codigo_ibge_estado, endereco, descricao, valor_aluguel, codigo_ibge_municipio)
    VALUES (1, 12345678909, 12, 'Rua A, Lt 1, Centro', 'Casa 2 quartos', 1200, 120001);
