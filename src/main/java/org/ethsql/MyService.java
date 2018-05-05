package org.ethsql;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.TransactionManager;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Arrays;

public class MyService extends HttpService {

    private Web3j web3j;
    private String contractAddress;
    private TransactionManager transactionManager;

    public MyService(String url, Web3j web3j, String contractAddress, TransactionManager transactionManager) {
        super(url);
        this.web3j = web3j;
        this.contractAddress = contractAddress;
        this.transactionManager = transactionManager;
    }

    public String getValueAsString(String tableName, String column, BigInteger rowNumber) {
        Function function = new Function("getValue",
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(tableName),
                        new org.web3j.abi.datatypes.Utf8String(column),
                        new org.web3j.abi.datatypes.generated.Uint256(rowNumber)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        String encodedFunction = FunctionEncoder.encode(function);
        try {
            Request<?, EthCall> request = web3j.ethCall(
                    Transaction.createEthCallTransaction(
                            transactionManager.getFromAddress(), contractAddress, encodedFunction),
                    DefaultBlockParameterName.LATEST);
            String payload = new ObjectMapper().writeValueAsString(request);
            try (InputStream result = performIO(payload)) {

                if (result != null) {
                    String value = IOUtils.toString(result, Charset.defaultCharset());
                    System.out.println(value);
                    return value;
//                        return objectMapper.readValue(result, String.class);
                } else {
                    return null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

//        System.out.println(
//                new MyService(
//                        clientConfiguration.getDeploymentNodeURL(),
//                        ethAccountClient.getWeb3j(),
//                        sqlStorage.getContractAddress(),
//                        sqlStorage.getTransactionManager()
//                ).getValueAsString("table1", "c1", BigInteger.ZERO)
//        );
