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

        System.out.println("Creating table...");
        sqlStorage.createTable("table1", "c1,c2,").send();

        System.out.println(sqlStorage.getColumnName("table1", BigInteger.ZERO).send());
        System.out.println(sqlStorage.getColumnName("table1", BigInteger.ONE).send());


//        System.out.println(
//                new MyService(
//                        clientConfiguration.getDeploymentNodeURL(),
//                        ethAccountClient.getWeb3j(),
//                        sqlStorage.getContractAddress(),
//                        sqlStorage.getTransactionManager()
//                ).runErrorProneMethod("table1", new BigInteger("2"), "c1,c2,", "bla,pepe,")
//        );

        System.out.println("Inserting data...");
        sqlStorage.insert("table1", new BigInteger("2"), "c1,c2,", "bla,pepe,").send();

        System.out.println("Get Inserted Data...");
        System.out.println(sqlStorage.getValue("table1", "c1", BigInteger.ZERO).send());
        System.out.println(sqlStorage.getValue("table1", "c2", BigInteger.ZERO).send());

        System.out.println("Select");
        System.out.println(sqlStorage.getSelect("table1", new BigInteger("2"), BigInteger.ZERO, BigInteger.ZERO, "c1,c2,").send());

        sqlStorage.insert("table1", new BigInteger("2"), "c1,c2,", "bla2,pepe2,").send();

        System.out.println("Select");
        System.out.println(sqlStorage.getSelect("table1", new BigInteger("2"), BigInteger.ONE, BigInteger.ZERO, "c1,c2,").send());

    }

    public static void main(String[] args) throws Exception {
        new Main().run();
    }
}
