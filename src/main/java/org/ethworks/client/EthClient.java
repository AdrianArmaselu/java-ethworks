package org.ethworks.client;

import org.ethworks.tx.RequestExecutor;
import org.ethworks.utils.EthereumUtils;
import org.ethworks.utils.Signer;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.Response;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.net.Socket;

import static org.ethworks.tx.RequestExecutor.executeRequest;
import static org.web3j.protocol.core.DefaultBlockParameterName.PENDING;

public class EthClient {
    protected Web3j web3j;
    protected byte chainId;

    public EthClient(ClientConfiguration clientConfiguration) {
        this(Web3j.build(
                new HttpService(clientConfiguration.getDeploymentNodeURL())),
                clientConfiguration.getChainId()
        );
    }

    public EthClient(Web3j web3j, byte chainId) {
        this.web3j = web3j;
        this.chainId = chainId;
    }

    public EthBlock.Block getPendingBlock() {
        return executeRequest(web3j.ethGetBlockByNumber(PENDING, true)).getBlock();
    }

    public BigInteger getNonce(String accountAddress) {
        return executeRequest(web3j.ethGetTransactionCount(accountAddress, PENDING)).getTransactionCount();
    }

    public BigInteger getBalance(String accountAddress) {
        return executeRequest(web3j.ethGetBalance(accountAddress, PENDING)).getBalance();
    }

    public BigInteger getCurrentGasPrice() {
        return executeRequest(web3j.ethGasPrice()).getGasPrice();
    }

    public BigInteger getGasLimit() {
        return getPendingBlock().getGasLimit();
    }

    public String createContractSignedTransaction(Credentials accountCredentials, String contractFilePath) throws IOException {
        String binary = EthereumUtils.compileToBinary(contractFilePath);
        RawTransaction rawTransaction = RawTransaction.createContractTransaction(
                getNonce(accountCredentials.getAddress()),
                getCurrentGasPrice(),
                getGasLimit(),
                BigInteger.ZERO,
                binary
        );
        return Signer.signTransaction(rawTransaction, getChainId(), accountCredentials);
    }

    public void sendRawTransactionAndReturnHash(String signedTransaction) {
         executeRequest(web3j.ethSendRawTransaction(signedTransaction));
    }

    public Response functionCall(String contractAddress, String from, String functionAsData) {
        Transaction call = Transaction.createEthCallTransaction(from, contractAddress, functionAsData);
        Request<?, EthCall> request = getWeb3j().ethCall(call, DefaultBlockParameterName.LATEST);
        return RequestExecutor.executeRequest(request);
    }

    public String signRawTransaction(RawTransaction rawTransaction, Credentials credentials) {
        return Signer.signTransaction(rawTransaction, getChainId(), credentials);
    }

    public <T extends Contract> T initializeContract(Class<T> type, String contractAddress, Credentials credentials) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<T> constructor = type.getDeclaredConstructor(
                String.class,
                Web3j.class,
                Credentials.class,
                BigInteger.class,
                BigInteger.class
        );
        constructor.setAccessible(true);
        return constructor.newInstance(
                contractAddress,
                getWeb3j(),
                credentials,
                getCurrentGasPrice(),
                getGasLimit()
        );
    }

    public byte getChainId() {
        return chainId;
    }

    public Web3j getWeb3j() {
        return web3j;
    }

    public static boolean isNodeRunning(String host, int port) {
        try {
            new Socket(host, port).close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
