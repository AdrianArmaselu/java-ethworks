package org.ethworks.client;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.Response;
import org.web3j.tx.Contract;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;

public class EthAccountClient extends EthClient {
    protected final Credentials accountCredentials;

    public EthAccountClient(ClientConfiguration clientConfiguration) {
        super(clientConfiguration);
        this.accountCredentials = clientConfiguration.getDeploymentAccountCredentials();
    }

    public EthAccountClient(Web3j web3j, byte chainId, Credentials accountCredentials) {
        super(web3j, chainId);
        this.accountCredentials = accountCredentials;
    }

    public BigInteger getNonce() {
        return super.getNonce(getAccountAddress());
    }

    public BigInteger getBalance() {
        return super.getBalance(getAccountAddress());
    }

    public String createContractSignedTransaction(String contractFilePath) throws IOException {
        return super.createContractSignedTransaction(getAccountCredentials(), contractFilePath);
    }

    public Response functionCall(String contractAddress, String functionAsData) {
        return super.functionCall(contractAddress, getAccountAddress(), functionAsData);
    }

    public String signRawTransaction(RawTransaction rawTransaction) {
        return super.signRawTransaction(rawTransaction, getAccountCredentials());
    }

    public <T extends Contract> T initializeContract(Class<T> type, String contractAddress) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return super.initializeContract(type, contractAddress, getAccountCredentials());
    }

    public Credentials getAccountCredentials() {
        return accountCredentials;
    }

    public String getAccountAddress(){
        return getAccountCredentials().getAddress();
    }

}
