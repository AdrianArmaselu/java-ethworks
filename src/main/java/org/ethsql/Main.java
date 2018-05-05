package org.ethsql;

import org.ethworks.client.ClientConfiguration;
import org.ethworks.client.EthAccountClient;
import org.ethworks.generated.SQLStorage;

import java.math.BigInteger;

public class Main {

    void run() throws Exception {
        ClientConfiguration clientConfiguration = new ClientConfiguration("src/main/resources/genache.conf");
        EthAccountClient ethAccountClient = new EthAccountClient(clientConfiguration);

        System.out.println("Account Address: " + clientConfiguration.getDeploymentAccountCredentials().getAddress());
        System.out.println("Account Private Key: " + clientConfiguration.getDeploymentAccountCredentials().getEcKeyPair().getPrivateKey());
        System.out.println("Account Public Key: " + clientConfiguration.getDeploymentAccountCredentials().getEcKeyPair().getPublicKey());
        System.out.println("Account Balance(wei): " + ethAccountClient.getBalance());

        System.out.println("Initializing contract...");
        SQLStorage sqlStorage = ethAccountClient.initializeContract(SQLStorage.class, null);
        SQLParser sqlParser = new SQLParser(sqlStorage);

        String query = "CREATE TABLE table1 (column1, column2, column3)";
        System.out.println("Creating table...");

        sqlParser.parseQuery(query);
        sqlStorage.createTable("table1", "c1,").send();

        query = "INSERT INTO table1 (column1, column2) VALUES (value11, value12)(value21, value22)(value31, value32)";
        System.out.println("Inserting data...");

        //sqlStorage.insert("table1", BigInteger.ONE, "c1", "bla");
        sqlParser.parseQuery(query);

        query = "SELECT * FROM table1";
        System.out.println("Retrieving data...");
        //System.out.println(sqlStorage.getValue("table1", "c1", BigInteger.ZERO).send());
        sqlParser.parseQuery(query);

        sqlStorage.insert("table1", BigInteger.ONE, "c1,", "bla,").send();

        System.out.println("Get Inserted Data...");
        System.out.println(sqlStorage.getValue("table1", "c1", BigInteger.ZERO).send());
    }

    public static void main(String[] args) throws Exception {
        new Main().run();
    }
}
