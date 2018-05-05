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
import org.web3j.crypto.CipherException;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.KeyPair;

public class Test {

    public static void main(String[] args) throws CipherException, IOException {

        BigInteger privateKey = new BigInteger("fff41e9a54ebb3aec2017cc5d9c76e1ef22cf3814438877bae2d42f08744bbfb", 16);
        BigInteger publicKey = new BigInteger("4f8615267a90a269cef5fede3e0823f0387d13f9", 16);
        ECKeyPair ecKeyPair = new ECKeyPair(privateKey, publicKey);
        WalletUtils.generateWalletFile("ethsql", ecKeyPair, new File("wallets/wallet2"), true);

        char c = 30;
        String s = "Asd" + c + "b";
        System.out.println(s);

    }
}
