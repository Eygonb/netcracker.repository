package com.netcracker.db.parser.csv;

import com.netcracker.db.entity.*;
import com.netcracker.db.repository.ContractRepository;
import com.netcracker.utils.List;
import com.netcracker.utils.impl.MyArrayList;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CsvLoader {
    public void loadRepository(String pathToFile, ContractRepository repository) {
        List<String[]> parsedCsv;

        try {
            parsedCsv = parseCsv(pathToFile);
        } catch (CsvValidationException | IOException | URISyntaxException e) {
            e.printStackTrace();
            parsedCsv = new MyArrayList<>();
        }

        List<Contract> contracts = parseContracts(parsedCsv);
        List<Client> clients = parseOwner(parsedCsv);

        for (int i = 0; i < contracts.size(); i++) {
            contracts.get(i).setContractOwner(clients.get(i));

            repository.save(contracts.get(i));
        }

    }

    private List<String[]> parseCsv(String pathToFile) throws URISyntaxException, IOException, CsvValidationException {
        Reader reader = Files.newBufferedReader(Paths.get(
                ClassLoader.getSystemResource(pathToFile).toURI()));

        CSVParser csvParser = new CSVParserBuilder()
                .withSeparator(';')
                .withIgnoreQuotations(true)
                .build();

        CSVReader csvReader = new CSVReaderBuilder(reader)
                .withCSVParser(csvParser)
                .withSkipLines(1)
                .build();

        List<String[]> contractLines = new MyArrayList<>();
        String[] contractLine;
        while ((contractLine = csvReader.readNext()) != null) {
            if (contractLine.length != 9) continue;
            contractLines.add(contractLine);
        }

        reader.close();
        csvReader.close();

        return contractLines;
    }

    private List<Contract> parseContracts(List<String[]> contractLines) {
        SimpleDateFormat dateTimeParser = new SimpleDateFormat("HH:mm dd.MM.yyyy");

        List<Contract> contracts = new MyArrayList<>();

        for (String[] string : contractLines) {
            Contract contract;
            switch (string[7]) {
                case "Mobile" -> {
                    MobileContract mobileContract = new MobileContract();

                    String[] customParameters = string[8].split(",");
                    if (customParameters.length == 3) {
                        mobileContract.setMinuteCount(Integer.parseInt(customParameters[0]));
                        mobileContract.setSmsCount(Integer.parseInt(customParameters[1]));
                        mobileContract.setTrafficCount(Integer.parseInt(customParameters[2]));
                    }

                    contract = mobileContract;
                }
                case "Wired" -> {
                    WiredInternetContract wiredInternetContract = new WiredInternetContract();

                    wiredInternetContract.setConnectionSpeed(Integer.parseInt(string[8]));

                    contract = wiredInternetContract;
                }
                case "DigitalTV" -> {
                    DigitalTVContract digitalTVContract = new DigitalTVContract();

                    digitalTVContract.setChannelPackage(string[8].split(","));

                    contract = digitalTVContract;
                }
                default -> contract = null;
            }

            if (contract == null) continue;

            try {
                contract.setContractStartDate(dateTimeParser.parse(string[4]));
            } catch (ParseException exception) {
                contract.setContractStartDate(null);
            }
            try {
                contract.setContractEndDate(dateTimeParser.parse(string[5]));
            } catch (ParseException exception) {
                contract.setContractEndDate(null);
            }

            contract.setContractNumber(string[6]);

            contracts.add(contract);
        }

        return contracts;
    }

    private List<Client> parseOwner(List<String[]> parsedCsv) {
        SimpleDateFormat dateTimeParser = new SimpleDateFormat("HH:mm dd.MM.yyyy");

        List<Client> result = new MyArrayList<>();

        for (String[] string : parsedCsv) {
            Client client = new Client();

            client.setFullName(string[0]);
            try {
                client.setBirthDate(dateTimeParser.parse(string[1]));
            } catch (ParseException exception) {
                client.setBirthDate(null);
            }
            try {
                client.setSex(SexEnum.valueOf(string[2]));
            } catch (IllegalArgumentException e) {
                client.setSex(null);
            }
            client.setPassportData(string[3]);

            result.add(client);
        }

        return result;
    }
}
