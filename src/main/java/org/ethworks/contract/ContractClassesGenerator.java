package org.ethworks.contract;

import org.apache.commons.io.FilenameUtils;
import org.ethereum.solidity.compiler.CompilationResult;
import org.ethworks.utils.EthereumUtils;
import org.web3j.codegen.SolidityFunctionWrapper;

import java.io.File;
import java.io.IOException;

import static org.ethworks.utils.EthereumUtils.loadContractDefinition;
import static org.web3j.codegen.Console.exitError;

public class ContractClassesGenerator {

    private String basePackageName = "org.ethworks.generated";
    private String filePath = "contracts/SQLStorage.sol";
    private String outputDir = "src/main/generated";
    private boolean isUseNativeJavaTypes = true;

    public ContractClassesGenerator(){
    }

    public ContractClassesGenerator(String basePackageName, String filePath, String outputDir, boolean isUseNativeJavaTypes){
        this.basePackageName = basePackageName;
        this.filePath = filePath;
        this.outputDir = outputDir;
        this.isUseNativeJavaTypes = isUseNativeJavaTypes;
    }

    public void generateJavaClasses() {
        File file = new File(filePath);
        if (file.isDirectory())
            generateJavaClasses(file);
        else
            generateJavaClass(file);
    }

    private void generateJavaClasses(File file) {
        File[] smartContractFiles = file.listFiles();
        if (smartContractFiles != null)
            for (File smartContractFile : smartContractFiles) {
                if (smartContractFile.isDirectory())
                    generateJavaClasses(smartContractFile);
                else {
                    generateJavaClass(smartContractFile);
                }
            }
    }

//  TODO: CHECK IF FILE IS ACTUALLY SOLIDITY FILE
    private void generateJavaClass(File smartContractFile) {
        try {
            System.out.println("Compiling " + smartContractFile);
            CompilationResult.ContractMetadata contractMetadata = EthereumUtils.compileToMetaData(smartContractFile);
            if (loadContractDefinition(contractMetadata.abi).isEmpty()) {
                exitError("Unable to parse input ABI file");
            } else {
                new SolidityFunctionWrapper(isUseNativeJavaTypes).generateJavaFiles(
                        FilenameUtils.getBaseName(smartContractFile.getName()),
                        contractMetadata.bin,
                        contractMetadata.abi,
                        outputDir,
                        basePackageName
                );
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ContractClassesGenerator().generateJavaClasses();
    }
}
