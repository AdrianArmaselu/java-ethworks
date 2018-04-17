package org.ethworks;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.apache.commons.io.FileUtils;
import org.ethworks.client.ClientConfiguration;
import org.ethworks.client.EthAccountClient;
import org.ethworks.client.EthClient;
import org.ethworks.contract.ContractClassesGenerator;
import org.ethworks.generated.SimpleStorage;
import org.ethworks.utils.EthereumUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;

public class Test {

    public static void main(String[] args) throws Exception {
        ClientConfiguration clientConfiguration = new ClientConfiguration("src/main/resources/genache.conf");
        Config config = ConfigFactory.parseResources("genache.conf");

//        String binary = EthereumUtils.compileToMetaData(new File("contracts/SimpleStorage.sol")).bin;
//        System.out.println(binary);
        String signed = new EthAccountClient(clientConfiguration).createContractSignedTransaction("contracts/SimpleStorage.sol");
        System.out.println(signed);
//        FileUtils.writeStringToFile(new File("contracts/SimpleStorage.signedtx"), signed, Charset.defaultCharset());
//        SimpleStorage simpleStorage = new EthAccountClient(clientConfiguration).initializeContract(SimpleStorage.class, null);

//        new EthAccountClient(clientConfiguration).sendRawTransactionAndReturnHash(signed);
//
//        new ContractClassesGenerator().generateJavaClasses();
//
        EthAccountClient ethClient = new EthAccountClient(clientConfiguration);

        Web3j web3j = Web3j.build(
                new HttpService(clientConfiguration.getDeploymentNodeURL()));


        String query = "Insert (id) values (1) into t1";

        String  tablename = "t1";
        String  value = "1";
        String columnName = "id";

        SimpleStorage simpleStorage = SimpleStorage.deploy(web3j, clientConfiguration.getDeploymentAccountCredentials(), ethClient.getBalance(), ethClient.getNonce()).send();

        simpleStorage.insert(tablename, columnName, value);


        String readQuery  = "Select * from t1";

        Column[] columns = simpleStorage.getColumns("t1 ");
        for (Column c : columns){
            simpleStorage.getValue(tablename, c);
        }

    }
}
