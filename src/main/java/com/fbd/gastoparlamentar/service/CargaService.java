package com.fbd.gastoparlamentar.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fbd.gastoparlamentar.model.*;
import com.fbd.gastoparlamentar.model.nonpersisted.LegislaturaDados;
import com.fbd.gastoparlamentar.model.nonpersisted.ParlamentarDados;
import com.fbd.gastoparlamentar.model.nonpersisted.PartidoDados;
import com.fbd.gastoparlamentar.repository.*;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

@Service
public class CargaService {

    Logger logger = LoggerFactory.getLogger(CargaService.class);


    @Autowired
    RestTemplate restTemplate;

    @Autowired
    LegislaturaRepository legislaturaRepository;

    @Autowired
    PartidoRepository partidoRepository;

    @Autowired
    UfRepository ufRepository;

    @Autowired
    ParlamentarRepository parlamentarRepository;

    @Autowired
    SubcotaRepository subcotaRepository;

    @Autowired
    FornecedorRepository fornecedorRepository;

    @Autowired
    DespesaRepository despesaRepository;

    @Autowired
    PassagemAereaRepository passagemAereaRepository;

    static private String URL_CAMARA =  "https://dadosabertos.camara.leg.br/api/v2";
    public String[] arquivos = {"2019.csv", "2020.csv", "2021.csv"};


    public void legislatura() throws JsonProcessingException {
        String legislaturas = restTemplate.getForObject(URL_CAMARA + "/legislaturas?ordem=DESC&ordenarPor=id", String.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        LegislaturaDados legislaturaDados = mapper.readValue(legislaturas, LegislaturaDados.class);

        logger.info("dando carga de legislatura");
        for (Legislatura l: legislaturaDados.getDados()) {
            legislaturaRepository.save(l);
        }
    }

    public void partido() throws JsonProcessingException {
        String partidos = restTemplate.getForObject(URL_CAMARA + "/partidos?itens=1000&ordem=ASC&ordenarPor=sigla", String.class);
        ObjectMapper mapper = new ObjectMapper();
        PartidoDados partidoDados = mapper.readValue(partidos, PartidoDados.class);

        logger.info("dando carga de partidos");
        for (Partido p: partidoDados.getDados()) {
            partidoRepository.save(p);
        }
    }

    public void parlamentar() throws JsonProcessingException {
        String parlamentares = restTemplate.getForObject(URL_CAMARA + "/deputados?itens=1000&ordem=ASC&ordenarPor=nome", String.class);
        ObjectMapper mapper = new ObjectMapper();
        ParlamentarDados parlamentarDados = mapper.readValue(parlamentares, ParlamentarDados.class);

        logger.info("dando carga de parlamentares");
        for (Parlamentar p: parlamentarDados.getDados()) {
            Partido partido = partidoRepository.findBySigla(p.getSiglaPartido());
            Uf uf = ufRepository.findBySigla(p.getSiglaUf());

            p.setIdPartido(partido.getId());
            p.setIdUf(uf.getId());
            p.setLideranca(false);

            parlamentarRepository.save(p);
        }
        logger.info("finalizando carga de parlamentares");

    }


    public void lideranca() {

        logger.info("carga lideranca");
        List<List<String>> records = new ArrayList<>();

        String nome = "";
        Integer id = 1;
        String codigoParlamentar = "";
        Integer legislatura = 0;
        for (String arquivo: arquivos) {
            try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/" + arquivo))) {

                //try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/2020.csv"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(";");
                    if (!values[8].contains("numSubCota")) {

                        nome = values[0].replace("\"","");
                        codigoParlamentar = values[2].replace("\"","");
                        legislatura = Integer.parseInt(values[7].replace("\"",""));

                        Parlamentar p = new Parlamentar();

                        if (codigoParlamentar.length() < 2) {
                            Parlamentar parlamentarBusca = parlamentarRepository.findByNome(nome);
                            if (parlamentarBusca == null) {
                                p.setLideranca(true);
                                p.setNome(nome);
                                p.setId(id);
                                p.setIdUf(28);
                                p.setIdPartido(1);
                                p.setIdLegislatura(legislatura);
                                parlamentarRepository.save(p);
                                id++;
                            }
                        }
                    }
                    //records.add(Arrays.asList(values));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        logger.info("finalizando carga lideranca");

        }
    }



    public void subCota() {
        logger.info("Carga de subcota");

        List<List<String>> records = new ArrayList<>();
        Integer codigo = 0;
        String sub = "";

        for (String arquivo: arquivos) {
            try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/" + arquivo))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(";");
                    if (!values[8].contains("numSubCota")) {
                        codigo = Integer.parseInt(values[8].replace("\"",""));
                        sub = values[9].replace("\"","");
                        Subcota subcota = new Subcota(codigo, sub);
                        subcotaRepository.save(subcota);
                        // logger.info(codigo + " " + sub);
                    }
                    //records.add(Arrays.asList(values));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void fornecedor() {
        logger.info("Carga de fornecedor");

        List<List<String>> records = new ArrayList<>();
        String cnpj = "";
        String descricao = "";

        for (String arquivo: arquivos) {
            try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/" + arquivo))) {
            // try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/2020.csv"))) {
                String line;
                int i = 0;
                while ((line = br.readLine()) != null) {
                    logger.info("fornecedor registro numero " + i);
                    String[] values = line.split(";");
                    if (!values[13].contains("txtCNPJCPF")) {
                        cnpj = values[13].replaceAll("[^0-9]", "");
                        descricao = values[12].replace("\"","");
                        if (isCNPJ(cnpj)) {
                            Fornecedor fornecedor = fornecedorRepository.findByCnpj(cnpj);
                            if (fornecedor == null) {
                                Fornecedor salvaFornecedor = new Fornecedor(descricao, cnpj, true);
                                fornecedorRepository.save(salvaFornecedor);
                            }
                        } else {
                            Fornecedor fornecedor = fornecedorRepository.findByNomeCnpj(descricao, cnpj);
                            if (fornecedor == null) {
                                Fornecedor salvaFornecedor = new Fornecedor(descricao, cnpj, false);
                                fornecedorRepository.save(salvaFornecedor);
                            }
                        }
                    }
                    i++;
                   // records.add(Arrays.asList(values));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        logger.info("Finalizando Carga de fornecedor");
    }

    public void despesa() {
        logger.info("Carga de despesa");

        List<List<String>> records = new ArrayList<>();

        String cnpj = "";
        String descricao = "";

        LocalDateTime dataEmissao;
        double valorDocumento = 0;
        double valorGlosa = 0;
        double valorLiquido = 0;
        int mes = 0;
        int ano = 0;
        String numLote = "";
        String ideDocumento = "";
        String urlDocumento = "";
        Fornecedor fornecedor;
        Integer idParlamentar = 0;
        Integer idSubcota = 0;
        String nomeParlamentar = "";


        for (String arquivo: arquivos) {
            try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/" + arquivo))) {
            //try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/2020.csv"))) {

            String line;
            int i = 0;

                while ((line = br.readLine()) != null) {
                    logger.info("despesa registro numero " + i);

                    String[] values = line.split(";");
                    if (!values[13].contains("txtCNPJCPF")) {
                        try {
                            cnpj = values[13].replaceAll("[^0-9]", "");
                            descricao = values[12].replace("\"", "");

                            if (isDateValid(values[16].replace("\"", "")))
                                dataEmissao = LocalDateTime.parse(values[16].replace("\"", ""));
                            else
                                dataEmissao = LocalDateTime.of(1900, Month.JANUARY, 01, 00, 00, 00);

                            if (NumberUtils.isParsable(values[17].replace("\"", "")))
                                valorDocumento = Double.parseDouble(values[17].replace("\"", ""));
                            else
                                valorDocumento = 0;

                            if (NumberUtils.isParsable(values[18].replace("\"", "")))
                                valorGlosa = Double.parseDouble(values[18].replace("\"", ""));
                            else
                                valorGlosa = 0;

                            if (NumberUtils.isParsable(values[19].replace("\"", "")))
                                valorLiquido = Double.parseDouble(values[19].replace("\"", ""));
                            else
                                valorLiquido = 0;

                            if (NumberUtils.isParsable(values[20].replace("\"", "")))
                                mes = Integer.parseInt(values[20].replace("\"", ""));
                            else
                                mes = 0;

                            if (NumberUtils.isParsable(values[21].replace("\"", "")))
                                ano = Integer.parseInt(values[21].replace("\"", ""));
                            else
                                ano = 0;

                            numLote = values[25].replace("\"", "");

                            ideDocumento = values[29].replace("\"", "");

                            urlDocumento = values[30].replace("\"", "");

                            if (NumberUtils.isParsable(values[8].replace("\"", "")))
                                idSubcota = Integer.parseInt(values[8].replace("\"", ""));
                            else
                                idSubcota = 0;

                            if (NumberUtils.isParsable(values[2].replace("\"", ""))) {
                                idParlamentar = Integer.parseInt(values[2].replace("\"", ""));
                            }
                            else {
                                nomeParlamentar = values[0].replace("\"", "");
                                Parlamentar p = parlamentarRepository.findByNome(nomeParlamentar);
                                if (p != null) {
                                    idParlamentar = p.getId();
                                }
                            }
                            if (isCNPJ(cnpj)) {
                                fornecedor = fornecedorRepository.findByCnpj(cnpj);

                                // salva normal a despesa
                            } else {
                                fornecedor = fornecedorRepository.findByNomeCnpj(descricao, cnpj);
                                // salva normal a despesa
                            }

                            Despesa despesa = new Despesa(
                                    dataEmissao, valorDocumento, valorGlosa, valorLiquido, mes, ano, numLote, ideDocumento, urlDocumento, idSubcota, fornecedor.getId(), idParlamentar
                            );
                            Despesa despesaSalva = despesaRepository.save(despesa);
                            String passageiros = values[23].replace("\"", "");
                            String trecho = values[24].replace("\"", "");
                            if (passageiros.length() > 2 || trecho.length() > 2) {
                                PassagemAerea passagemAerea = new PassagemAerea(passageiros, trecho, despesaSalva.getId());
                                logger.info("salvando passagem aerea");
                                passagemAereaRepository.save(passagemAerea);
                            }

                    } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }



                    }
                    // records.add(Arrays.asList(values));
                    i++;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        logger.info("Finalizando Carga de despesa");
    }

    public boolean isDateValid(String dateStr) {
        try {
            LocalDateTime.parse(dateStr);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }


    public boolean isCNPJ(String CNPJ) {

        // considera-se erro CNPJ's formados por uma sequencia de numeros iguais
        if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111") ||
                CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333") ||
                CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555") ||
                CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777") ||
                CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999") ||
                (CNPJ.length() != 14))
            return(false);

        char dig13, dig14;
        int sm, i, r, num, peso;

        // "try" - protege o c??digo para eventuais erros de conversao de tipo (int)
        try {

            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i=11; i>=0; i--) {
                // converte o i-??simo caractere do CNPJ em um n??mero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posi????o de '0' na tabela ASCII)
                num = (int)(CNPJ.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10)
                    peso = 2;
            }

            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig13 = '0';
            else dig13 = (char)((11-r) + 48);

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i=12; i>=0; i--) {
                num = (int)(CNPJ.charAt(i)- 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10)
                    peso = 2;
            }

            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig14 = '0';
            else dig14 = (char)((11-r) + 48);

            // Verifica se os d??gitos calculados conferem com os d??gitos informados.
            if ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13)))
                return(true);
            else return(false);
        } catch (InputMismatchException erro) {
            return(false);
        }
    }

}
