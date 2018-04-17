package org.ethworks.client;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigParseOptions;
import org.apache.commons.io.FileUtils;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class ClientConfiguration {

    private Config config;
    private Credentials deploymentAccountCredentials;

    public ClientConfiguration(String filePath) {
        config = ConfigFactory.parseFile(new File(filePath), ConfigParseOptions.defaults());
    }

    private Credentials loadDeploymentAccountCredentials() {
        File deploymentAccountFile = new File(config.getString("accountFilePath"));
        try {
            File deploymentAccountPasswordFile = new File(config.getString("passwordFilePath"));
            String deploymentAccountPassword = FileUtils.readFileToString(deploymentAccountPasswordFile, Charset.defaultCharset()).trim();
            return WalletUtils.loadCredentials(deploymentAccountPassword, deploymentAccountFile);
        } catch (IOException | CipherException e) {
            throw new RuntimeException("Could not load Credentials from account file " + deploymentAccountFile + ":\n" + e.getMessage());
        }
    }

    public String getDeploymentNodeURL() {
        return config.getString("url");
    }

    public byte getChainId(){
        return (byte) config.getInt("chainId");
    }

    public Credentials getDeploymentAccountCredentials() {
        if(deploymentAccountCredentials == null)
            deploymentAccountCredentials = loadDeploymentAccountCredentials();
        return deploymentAccountCredentials;
    }
}
