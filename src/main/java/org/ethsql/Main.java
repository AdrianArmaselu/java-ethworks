package org.ethsql;

import io.netty.util.concurrent.CompleteFuture;
import org.apache.commons.lang3.StringUtils;
import org.ethworks.client.ClientConfiguration;
import org.ethworks.client.EthAccountClient;
import org.ethworks.generated.SQLStorage;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.ClientTransactionManager;
import org.web3j.tx.TransactionManager;

import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class Main {

    public static void main(String[] args) throws Exception {
        ClientConfiguration clientConfiguration = new ClientConfiguration("src/main/resources/genache.conf");
        EthAccountClient ethAccountClient = new EthAccountClient(clientConfiguration);

        System.out.println(clientConfiguration.getDeploymentAccountCredentials().getAddress());
        System.out.println(clientConfiguration.getDeploymentAccountCredentials().getEcKeyPair().getPrivateKey());
        System.out.println(clientConfiguration.getDeploymentAccountCredentials().getEcKeyPair().getPublicKey());

        System.out.println(ethAccountClient.getBalance());


        System.out.println("Initializing contract...");
        SQLStorage sqlStorage = ethAccountClient.initializeContract(SQLStorage.class, null);
        SQLParser sqlParser = new SQLParser(sqlStorage);

        String query = "CREATE TABLE table1 (column1, column2, column3)";
        System.out.println("Creating table...");
        sqlParser.parseQuery(query);

        query = "INSERT INTO table1 (column1, column2) VALUES (value11, value12)(value21, value22)(value31, value32)";
        System.out.println("Inserting data...");
        //sqlStorage.insert("table1", BigInteger.ONE, "c1", "bla");
        sqlParser.parseQuery(query);

        query = "SELECT * FROM table1";
        System.out.println("Retrieving data...");
        //System.out.println(sqlStorage.getValue("table1", "c1", BigInteger.ZERO).send());
        sqlParser.parseQuery(query);



    }
}

/*
HttpService httpService = new HttpService();
        httpService

        Function function = new Function("getValue",
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String("table1"),
                        new org.web3j.abi.datatypes.Utf8String("c1"),
                        new org.web3j.abi.datatypes.generated.Uint256(BigInteger.ZERO)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));

        String encodedFunction = FunctionEncoder.encode(function);
        org.web3j.protocol.core.methods.response.EthCall ethCall = null;
        try {
            ethCall = ethAccountClient.getWeb3j().ethCall(
                    Transaction.createEthCallTransaction(
                            ethAccountClient.getAccountAddress(), sqlStorage.getContractAddress(), encodedFunction),
                    DefaultBlockParameterName.LATEST)
                    .send();
        }catch (Exception e){
            System.out.println(ethCall.getError());

        }
 */
