package org.ethsql;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import org.ethworks.CommandLine;
import org.ethworks.client.ClientConfiguration;
import org.ethworks.client.EthAccountClient;
import org.ethworks.generated.SQLStorage;

import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;

public class Main {

    public static void main(String[] args) throws Exception {
        ClientConfiguration clientConfiguration = new ClientConfiguration("src/main/resources/genache.conf");
        EthAccountClient ethAccountClient = new EthAccountClient(clientConfiguration);

        System.out.println("Account Address: " + clientConfiguration.getDeploymentAccountCredentials().getAddress());
        System.out.println("Account Private Key: " + clientConfiguration.getDeploymentAccountCredentials().getEcKeyPair().getPrivateKey());
        System.out.println("Account Public Key: " + clientConfiguration.getDeploymentAccountCredentials().getEcKeyPair().getPublicKey());
        System.out.println("Account Balance(wei): " + ethAccountClient.getBalance());

        System.out.println("Initializing contract...");
        SQLStorage sqlStorage = ethAccountClient.initializeContract(SQLStorage.class, null);
        SQLParser sqlParser = new SQLParser(sqlStorage);
        System.out.println();

        /*
        Query format:
            CREATE:
                CREATE TABLE <table name> (<c1>, <c2>, ..., <cn>)

            INSERT:
                INSERT INTO <table name> (<c1>, <c2>, ..., <cn>) VALUES (<v11>, <v12>, ..., <vin>)(<v21>, <v22>, ..., <vjn>)...()

            SELECT:
                SELECT <columns1, columns2, ..., columnsn> FROM <table name>

            UPDATE:
                UPDATE <table name> SET (<c1> = <v1>, <c2> = <v2>, ..., <cn> = <vn>) WHERE <row index>
        */

        String query = "CREATE TABLE People (SSN, Name, Age, Occupation)";
        System.out.println(query);
        System.out.println("Creating table...");
        sqlParser.parseQuery(query);
        System.out.println();

        query = "INSERT INTO People (SSN, Name, Age, Occupation) VALUES (1111, Bill, 22, Student)(2222, Jane, 23, System Engineer)(33333, Alice, 34, Doctor)";
        System.out.println(query);
        System.out.println("Inserting data...");
        sqlParser.parseQuery(query);
        System.out.println();

        query = "SELECT SSN FROM People";
        System.out.println(query);
        System.out.println("Retrieving data...");
        sqlParser.parseQuery(query);
        query = "SELECT Name FROM People";
        System.out.println(query);
        System.out.println("Retrieving data...");
        sqlParser.parseQuery(query);
        query = "SELECT Age FROM People";
        System.out.println(query);
        System.out.println("Retrieving data...");
        sqlParser.parseQuery(query);
        query = "SELECT Occupation FROM People";
        System.out.println(query);
        System.out.println("Retrieving data...");
        sqlParser.parseQuery(query);

        query = "UPDATE People SET SSN = 4444, Name = Jack, Age = 46, Occupation = Lumberjack WHERE 0";
        System.out.println(query);
        System.out.println("Updating data...");
        sqlParser.parseQuery(query);
        System.out.println();


        query = "SELECT SSN FROM People";
        System.out.println(query);
        System.out.println("Retrieving data...");
        sqlParser.parseQuery(query);
        query = "SELECT Name FROM People";
        System.out.println(query);
        System.out.println("Retrieving data...");
        sqlParser.parseQuery(query);
        query = "SELECT Age FROM People";
        System.out.println(query);
        System.out.println("Retrieving data...");
        sqlParser.parseQuery(query);
        query = "SELECT Occupation FROM People";
        System.out.println(query);
        System.out.println("Retrieving data...");
        sqlParser.parseQuery(query);
    }

    /*
    void run(String query) throws Exception {
        ClientConfiguration clientConfiguration = new ClientConfiguration("src/main/resources/genache.conf");
        EthAccountClient ethAccountClient = new EthAccountClient(clientConfiguration);

        System.out.println("Account Address: " + clientConfiguration.getDeploymentAccountCredentials().getAddress());
        System.out.println("Account Private Key: " + clientConfiguration.getDeploymentAccountCredentials().getEcKeyPair().getPrivateKey());
        System.out.println("Account Public Key: " + clientConfiguration.getDeploymentAccountCredentials().getEcKeyPair().getPublicKey());
        System.out.println("Account Balance(wei): " + ethAccountClient.getBalance());

        System.out.println("Initializing contract...");
        SQLStorage sqlStorage = ethAccountClient.initializeContract(SQLStorage.class, null);
        SQLParser sqlParser = new SQLParser(sqlStorage);
        sqlParser.parseQuery(query);
    }

    private void executeCommands(String[] args) throws Exception {
        SqlArgs sqlArgs = new SqlArgs();
        JCommander.newBuilder()
                .addObject(sqlArgs)
                .build()
                .parse(args);
        Main main = new Main();
        main.run(sqlArgs.getQuery());
    }

    public static void main(String[] args) throws Exception {
//        new Main().executeCommands(args);
    }

    private class SqlArgs{

        @Parameter(names = {"--query", "-q"}, description = "the query to execute")
        private String query;

        public String getQuery() {
            return query;
        }
    }
    */
}
