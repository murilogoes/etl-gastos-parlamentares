CREATE TABLE IF NOT EXISTS `gastoparlamentar`.`legislatura` (
  `id_legislatura` INT NOT NULL,
  `data_inicio` DATE NOT NULL,
  `data_fim` DATE NOT NULL,
  PRIMARY KEY (`id_legislatura`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `gastoparlamentar`.`partido` (
  `id_partido` INT NOT NULL,
  `sigla` VARCHAR(45) NULL,
  `nome` VARCHAR(100) NULL,
  PRIMARY KEY (`id_partido`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `gastoparlamentar`.`uf` (
  `id_uf` INT NOT NULL AUTO_INCREMENT,
  `sigla` VARCHAR(45) NULL,
  `descricao` VARCHAR(45) NULL,
  PRIMARY KEY (`id_uf`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `gastoparlamentar`.`parlamentar` (
  `id_parlamentar` INT NOT NULL,
  `nome` VARCHAR(200) NULL,
  `id_legislatura` INT NOT NULL,
  `id_partido` INT NOT NULL,
  `id_uf` INT NOT NULL,
  `lideranca` TINYINT NOT NULL,
  PRIMARY KEY (`id_parlamentar`),
  INDEX `fk_parlamentar_legislatura1_idx` (`id_legislatura` ASC),
  INDEX `fk_parlamentar_partido1_idx` (`id_partido` ASC),
  INDEX `fk_parlamentar_uf1_idx` (`id_uf` ASC),
  CONSTRAINT `fk_parlamentar_legislatura1`
    FOREIGN KEY (`id_legislatura`)
    REFERENCES `gastoparlamentar`.`legislatura` (`id_legislatura`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_parlamentar_partido1`
    FOREIGN KEY (`id_partido`)
    REFERENCES `gastoparlamentar`.`partido` (`id_partido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_parlamentar_uf1`
    FOREIGN KEY (`id_uf`)
    REFERENCES `gastoparlamentar`.`uf` (`id_uf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `gastoparlamentar`.`subcota` (
  `id_subcota` INT NOT NULL,
  `descricao` VARCHAR(1000) NULL,
  PRIMARY KEY (`id_subcota`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `gastoparlamentar`.`fornecedor` (
  `id_fornecedor` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(1000) NULL,
  `cnpj` VARCHAR(45) NULL,
  `cnpj_valido` TINYINT NOT NULL,
  PRIMARY KEY (`id_fornecedor`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `gastoparlamentar`.`despesa` (
  `id_despesa` INT NOT NULL AUTO_INCREMENT,
  `ide_documento` VARCHAR(50) NULL,
  `url_documento` VARCHAR(200) NULL,
  `num_lote` VARCHAR(50) NULL,
  `data_emissao` DATETIME NULL,
  `valor_documento` DOUBLE NULL,
  `valor_glosa` DOUBLE NULL,
  `valor_liquido` DOUBLE NULL,
  `mes` INT NULL,
  `ano` INT NULL,
  `id_subcota` INT NOT NULL,
  `id_fornecedor` INT NOT NULL,
  `id_parlamentar` INT NOT NULL,
  PRIMARY KEY (`id_despesa`),
  INDEX `fk_despesa_subcota_idx` (`id_subcota` ASC),
  INDEX `fk_despesa_fornecedor1_idx` (`id_fornecedor` ASC),
  INDEX `fk_despesa_parlamentar1_idx` (`id_parlamentar` ASC),
  CONSTRAINT `fk_despesa_subcota`
    FOREIGN KEY (`id_subcota`)
    REFERENCES `gastoparlamentar`.`subcota` (`id_subcota`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_despesa_fornecedor1`
    FOREIGN KEY (`id_fornecedor`)
    REFERENCES `gastoparlamentar`.`fornecedor` (`id_fornecedor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_despesa_parlamentar1`
    FOREIGN KEY (`id_parlamentar`)
    REFERENCES `gastoparlamentar`.`parlamentar` (`id_parlamentar`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'subcota_id_subcota';

CREATE TABLE IF NOT EXISTS `gastoparlamentar`.`passagem_aerea` (
  `id_passagem_aerea` INT NOT NULL AUTO_INCREMENT,
  `passageiro` VARCHAR(1000) NULL,
  `trecho` VARCHAR(200) NULL,
  `id_despesa` INT NOT NULL,
  PRIMARY KEY (`id_passagem_aerea`),
  INDEX `fk_passagem_aerea_despesa1_idx` (`id_despesa` ASC),
  CONSTRAINT `fk_passagem_aerea_despesa1`
    FOREIGN KEY (`id_despesa`)
    REFERENCES `gastoparlamentar`.`despesa` (`id_despesa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO gastoparlamentar.uf (`sigla`, `descricao`) VALUES ('AC', 'Acre');
INSERT INTO gastoparlamentar.uf (`sigla`, `descricao`) VALUES ('AL', 'Alagoas');
INSERT INTO gastoparlamentar.uf (`sigla`, `descricao`) VALUES ('AP', 'Amapá');
INSERT INTO gastoparlamentar.uf (`sigla`, `descricao`) VALUES ('AM', 'Amazonas');
INSERT INTO gastoparlamentar.uf (`sigla`, `descricao`) VALUES ('BA', 'Bahia');
INSERT INTO gastoparlamentar.uf (`sigla`, `descricao`) VALUES ('CE', 'Ceará');
INSERT INTO gastoparlamentar.uf (`sigla`, `descricao`) VALUES ('DF', 'Distrito Federal');
INSERT INTO gastoparlamentar.uf (`sigla`, `descricao`) VALUES ('ES', 'Espírito Santo');
INSERT INTO gastoparlamentar.uf (`sigla`, `descricao`) VALUES ('GO', 'Goiás');
INSERT INTO gastoparlamentar.uf (`sigla`, `descricao`) VALUES ('MA', 'Maranhão');
INSERT INTO gastoparlamentar.uf (`sigla`, `descricao`) VALUES ('MT', 'Mato Grosso');
INSERT INTO gastoparlamentar.uf (`sigla`, `descricao`) VALUES ('MS', 'Mato Grosso do Sul');
INSERT INTO gastoparlamentar.uf (`sigla`, `descricao`) VALUES ('MG', 'Minas Gerais');
INSERT INTO gastoparlamentar.uf (`sigla`, `descricao`) VALUES ('PA', 'Pará');
INSERT INTO gastoparlamentar.uf (`sigla`, `descricao`) VALUES ('PB', 'Paraíba');
INSERT INTO gastoparlamentar.uf (`sigla`, `descricao`) VALUES ('PR', 'Paraná');
INSERT INTO gastoparlamentar.uf (`sigla`, `descricao`) VALUES ('PE', 'Pernambuco');
INSERT INTO gastoparlamentar.uf (`sigla`, `descricao`) VALUES ('PI', 'Piauí');
INSERT INTO gastoparlamentar.uf (`sigla`, `descricao`) VALUES ('RJ', 'Rio de Janeiro');
INSERT INTO gastoparlamentar.uf (`sigla`, `descricao`) VALUES ('RN', 'Rio Grande do Norte');
INSERT INTO gastoparlamentar.uf (`sigla`, `descricao`) VALUES ('RS', 'Rio Grande do Sul');
INSERT INTO gastoparlamentar.uf (`sigla`, `descricao`) VALUES ('RO', 'Rondônia');
INSERT INTO gastoparlamentar.uf (`sigla`, `descricao`) VALUES ('RR', 'Roraima');
INSERT INTO gastoparlamentar.uf (`sigla`, `descricao`) VALUES ('SC', 'Santa Catarina');
INSERT INTO gastoparlamentar.uf (`sigla`, `descricao`) VALUES ('SP', 'São Paulo');
INSERT INTO gastoparlamentar.uf (`sigla`, `descricao`) VALUES ('SE', 'Sergipe');
INSERT INTO gastoparlamentar.uf (`sigla`, `descricao`) VALUES ('TO', 'Tocantins');
INSERT INTO gastoparlamentar.uf (`sigla`, `descricao`) VALUES ('NA', 'Sem Estado Definido');

INSERT INTO `gastoparlamentar`.`partido`
(`id_partido`,`sigla`,`nome`)
VALUES (1,'NA', 'Liderança de Partido');