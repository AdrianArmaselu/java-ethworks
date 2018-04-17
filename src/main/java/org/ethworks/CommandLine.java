package org.ethworks;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.ethworks.client.ClientConfiguration;
import org.ethworks.client.EthAccountClient;
import org.ethworks.contract.ContractClassesGenerator;
import org.ethworks.utils.EthereumUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

// TODO: SPECIFY NETWORK ARGUMENTS AND WRAP THEM IN A CLIENT CONFIG OBJECT
// TODO: IMPOSE RESTRICTIONS ON ARGUMENTS
public class CommandLine {

    private EthAccountClient ethClient;

    private void executeCommands(String[] args) {
        MainArgs mainArgs = new MainArgs();
        SignContractTxArgs signContractTxArgs = new SignContractTxArgs();
        ConvertToJavaClassArgs convertToJavaClassArgs = new ConvertToJavaClassArgs();

        JCommander.newBuilder()
                .addObject(mainArgs)
                .addObject(signContractTxArgs)
                .addObject(convertToJavaClassArgs)
                .build()
                .parse(args);

        if (!StringUtils.isEmpty(mainArgs.configFilePath))
            ethClient = new EthAccountClient(new ClientConfiguration(mainArgs.configFilePath));


        if (convertToJavaClassArgs.isPresent())
            new ContractClassesGenerator().generateJavaClasses();


        if (signContractTxArgs.isPresent()) {
            loadCompileSignAndSaveContract(signContractTxArgs.getContractFilePath(), signContractTxArgs.getSignedTxFilePath());
        }

        if (mainArgs.init) {
        }
    }

    private void loadCompileAndSaveContract(String contractFilePath, String outputFilePath){
        try {
            String binary = EthereumUtils.compileToMetaData(contractFilePath).bin;
            FileUtils.writeStringToFile(new File(outputFilePath), binary, Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadCompileSignAndSaveContract(String contractFilePath, String signedTxFilePath) {
        try {
            String signedTx = ethClient.createContractSignedTransaction(contractFilePath);
            FileUtils.write(new File(signedTxFilePath), signedTx, Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        CommandLine commandLine = new CommandLine();
        commandLine.executeCommands(args);
    }

    private class MainArgs{

        @Parameter(names = "--help", help = true)
        private boolean help;

        @Parameter(names = {"--network-url", "-url"}, description = "network url")
        private String networkUrl;

        @Parameter(names = {"--init", "-i"}, description = "initialize a project")
        private boolean init;

        @Parameter(names = {"--config-file", "-cf"}, description = "configuration file for client")
        private String configFilePath;

    }

    private class SignContractTxArgs {
        @Parameter(names = {"--sign", "-s"}, description = "compile and sign smart contract to output file", arity = 2)
        private List<String> signArgs;

        boolean isPresent() {
            return CollectionUtils.isNotEmpty(signArgs);
        }

        String getContractFilePath() {
            return signArgs.get(0);
        }

        String getSignedTxFilePath() {
            return signArgs.get(1);
        }
    }

    private class ConvertToJavaClassArgs {
        @Parameter(names = {"--convert", "-c"}, description = "convert smart contract into java class", arity = 2)
        private List<String> conversionArgs;

        public boolean isPresent() {
            return CollectionUtils.isNotEmpty(conversionArgs);
        }
    }

}
