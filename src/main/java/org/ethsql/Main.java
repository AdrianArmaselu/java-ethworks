package org.ethsql;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.ethworks.client.ClientConfiguration;
import org.ethworks.client.EthAccountClient;
import org.ethworks.generated.SQLStorage;

import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;

public class Main {

    void run() throws Exception {
//        ClientConfiguration clientConfiguration = new ClientConfiguration("src/main/resources/genache.conf");
//        EthAccountClient ethAccountClient = new EthAccountClient(clientConfiguration);
//
//        System.out.println("Account Address: " + clientConfiguration.getDeploymentAccountCredentials().getAddress());
//        System.out.println("Account Private Key: " + clientConfiguration.getDeploymentAccountCredentials().getEcKeyPair().getPrivateKey());
//        System.out.println("Account Public Key: " + clientConfiguration.getDeploymentAccountCredentials().getEcKeyPair().getPublicKey());
//        System.out.println("Account Balance(wei): " + ethAccountClient.getBalance());
//
//        System.out.println("Initializing contract...");
//        SQLStorage sqlStorage = ethAccountClient.initializeContract(SQLStorage.class, null);
//
//        System.out.println("Creating table...");
//        sqlStorage.createTable("table1", "c1,c2,").send();
//
//        System.out.println(sqlStorage.getColumnName("table1", BigInteger.ZERO).send());
//        System.out.println(sqlStorage.getColumnName("table1", BigInteger.ONE).send());
//
//
////        System.out.println(
////                new MyService(
////                        clientConfiguration.getDeploymentNodeURL(),
////                        ethAccountClient.getWeb3j(),
////                        sqlStorage.getContractAddress(),
////                        sqlStorage.getTransactionManager()
////                ).runErrorProneMethod("table1", new BigInteger("2"), "c1,c2,", "bla,pepe,|adi,ana,")
////        );
//
//        System.out.println("Inserting data...");
//        sqlStorage.insert("table1", new BigInteger("2"), "c1,c2,", "bla,pepe,|adi,ana,|adi2,ana2,").send();
//
//        System.out.println("Get Inserted Data...");
//        System.out.println(sqlStorage.getValue("table1", "c1", BigInteger.ZERO).send());
//        System.out.println(sqlStorage.getValue("table1", "c2", BigInteger.ZERO).send());
//
//        System.out.println("Select");
//        System.out.println(sqlStorage.getSelect("table1", new BigInteger("2"), BigInteger.ZERO, BigInteger.ZERO, "c1,c2,").send());
//
//        sqlStorage.insert("table1", new BigInteger("2"), "c1,c2,", "bla2,pepe2,").send();
//
//        System.out.println("Select");
//        System.out.println(sqlStorage.getSelect("table1", new BigInteger("2"), BigInteger.ZERO, BigInteger.ZERO, "c1,c2,").send());
//        System.out.println(sqlStorage.getSelect("table1", new BigInteger("2"), BigInteger.ONE, BigInteger.ZERO, "c1,c2,").send());
//        System.out.println(sqlStorage.getSelect("table1", new BigInteger("2"), new BigInteger("2"), BigInteger.ZERO, "c1,c2,").send());
//        System.out.println(sqlStorage.getSelect("table1", new BigInteger("2"), new BigInteger("3"), BigInteger.ZERO, "c1,c2,").send());
//
////        sqlStorage.update("table1", new BigInteger("2"),  BigInteger.ZERO, "c1,c2,", "bla3,pepe3,").send();
//
////        System.out.println("Select");
////        System.out.println(sqlStorage.getSelect("table1", new BigInteger("2"), BigInteger.ZERO, BigInteger.ZERO, "c1,c2,").send());
//
//        System.out.println(sqlStorage.getSelect("table1", new BigInteger("1"), BigInteger.ZERO, BigInteger.ZERO, "c1,").send());
//        System.out.println(sqlStorage.getSelect("table1", new BigInteger("1"), BigInteger.ONE, BigInteger.ZERO, "c1,").send());

//        test();
        stressTest();

    }


    void test() throws Exception {
        ClientConfiguration clientConfiguration = new ClientConfiguration("src/main/resources/genache.conf");
        EthAccountClient ethAccountClient = new EthAccountClient(clientConfiguration);

        System.out.println("Account Address: " + clientConfiguration.getDeploymentAccountCredentials().getAddress());
        System.out.println("Account Private Key: " + clientConfiguration.getDeploymentAccountCredentials().getEcKeyPair().getPrivateKey());
        System.out.println("Account Public Key: " + clientConfiguration.getDeploymentAccountCredentials().getEcKeyPair().getPublicKey());
        System.out.println("Account Balance(wei): " + ethAccountClient.getBalance());

        System.out.println("Initializing contract...");
        SQLStorage sqlStorage = ethAccountClient.initializeContract(SQLStorage.class, null);

        System.out.println("Creating table...");
        sqlStorage.createTable("table1", "c1,c2,c3,").send();

        System.out.println("Inserting data...");
        sqlStorage.insert("table1", new BigInteger("3"), "c1,c2,c3,", "bla,pepe,hehe,").send();
        sqlStorage.insert("table1", new BigInteger("3"), "c1,c2,c3,", "bla2,pepe2,hehe2,").send();
        sqlStorage.insert("table1", new BigInteger("3"), "c1,c2,c3,", "bla3,pepe3,hehe3,").send();

        System.out.println("Get Inserted Data...");
        System.out.println(sqlStorage.getValue("table1", "c1", BigInteger.ZERO).send());
        System.out.println(sqlStorage.getValue("table1", "c1", BigInteger.ONE).send());
        System.out.println(sqlStorage.getValue("table1", "c1", new BigInteger("2")).send());
        System.out.println(sqlStorage.getValue("table1", "c2", BigInteger.ZERO).send());
        System.out.println(sqlStorage.getValue("table1", "c2", BigInteger.ONE).send());
        System.out.println(sqlStorage.getValue("table1", "c2", new BigInteger("2")).send());
        System.out.println(sqlStorage.getValue("table1", "c3", BigInteger.ZERO).send());
        System.out.println(sqlStorage.getValue("table1", "c3", BigInteger.ONE).send());
        System.out.println(sqlStorage.getValue("table1", "c3", new BigInteger("2")).send());
    }

    void stressTest() throws Exception {
        ClientConfiguration clientConfiguration = new ClientConfiguration("src/main/resources/genache.conf");
        EthAccountClient ethAccountClient = new EthAccountClient(clientConfiguration);

        System.out.println("Account Address: " + clientConfiguration.getDeploymentAccountCredentials().getAddress());
        System.out.println("Account Private Key: " + clientConfiguration.getDeploymentAccountCredentials().getEcKeyPair().getPrivateKey());
        System.out.println("Account Public Key: " + clientConfiguration.getDeploymentAccountCredentials().getEcKeyPair().getPublicKey());
        System.out.println("Account Balance(wei): " + ethAccountClient.getBalance());

        System.out.println("Initializing contract...");
        SQLStorage sqlStorage = ethAccountClient.initializeContract(SQLStorage.class, null);

        System.out.println("Creating table...");
        RandomStringUtils.random(10);
        StringBuilder columns = new StringBuilder();
        for(int i = 0  ;i < 62 ; i++){
            columns.append(RandomStringUtils.random(10)).append(",");
        }
        sqlStorage.createTable("table1", columns.toString()).send();

    }

    public static void main(String[] args) throws Exception {
        new Main().run();
    }
}
