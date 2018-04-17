package org.ethworks.generated;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version null.
 */
public class SimpleStorage extends Contract {
    private static final String BINARY = "6060604052341561000f57600080fd5b60008081905550610109806100256000396000f3006060604052600436106053576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063209652551460585780633fa4f24514607e578063552410771460a4575b600080fd5b3415606257600080fd5b606860c4565b6040518082815260200191505060405180910390f35b3415608857600080fd5b608e60cd565b6040518082815260200191505060405180910390f35b341560ae57600080fd5b60c2600480803590602001909190505060d3565b005b60008054905090565b60005481565b80600081905550505600a165627a7a723058202c532e63995adb42e42d653dd314985ed613a323c8a9bd0713a505dbe6a87eaa0029";

    protected SimpleStorage(String contractAddress, Web3j web3j, Credentials accountCredentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, accountCredentials, gasPrice, gasLimit);
        try {
                    send(null, BINARY, BigInteger.ZERO, gasPrice, gasLimit);
                } catch (IOException | TransactionException e) {
                    e.printStackTrace();
                }
    }

    protected SimpleStorage(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
        try {
                    send(null, BINARY, BigInteger.ZERO, gasPrice, gasLimit);
                } catch (IOException | TransactionException e) {
                    e.printStackTrace();
                }
    }

    public RemoteCall<BigInteger> getValue() {
        Function function = new Function("getValue", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> value() {
        Function function = new Function(
                "value", 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setValue(BigInteger val) {
        Function function = new Function(
                "setValue", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(val)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<SimpleStorage> deploy(Web3j web3j, Credentials accountCredentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SimpleStorage.class, web3j, accountCredentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<SimpleStorage> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SimpleStorage.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static SimpleStorage load(String contractAddress, Web3j web3j, Credentials accountCredentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SimpleStorage(contractAddress, web3j, accountCredentials, gasPrice, gasLimit);
    }

    public static SimpleStorage load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SimpleStorage(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
