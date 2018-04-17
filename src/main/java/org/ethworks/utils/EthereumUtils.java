package org.ethworks.utils;


import org.ethereum.solidity.compiler.CompilationResult;
import org.ethereum.solidity.compiler.SolidityCompiler;
import org.web3j.protocol.ObjectMapperFactory;
import org.web3j.protocol.core.methods.response.AbiDefinition;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.ethereum.solidity.compiler.SolidityCompiler.Options.*;

public class EthereumUtils {

    public static String compileToBinary(String filePath) throws IOException {
        return compileToMetaData(new File(filePath)).bin;
    }

    public static CompilationResult.ContractMetadata compileToMetaData(String filePath) throws IOException {
        return compileToMetaData(new File(filePath));
    }

    public static CompilationResult.ContractMetadata compileToMetaData(File file) throws IOException {
        SolidityCompiler.Result res = SolidityCompiler.compile(file, true, ABI, BIN, INTERFACE, METADATA);
        if (res.isFailed()) throw new IOException("Failed to compileToMetadata. Reason: \n" + res.errors);
        CompilationResult result = CompilationResult.parse(res.output);
        return result.contracts.values().iterator().next();
    }

    public static CompilationResult.ContractMetadata compileToMetadata(String contractSourceCode) throws IOException {
        SolidityCompiler.Result res = SolidityCompiler.compile(contractSourceCode.getBytes(), true, ABI, BIN, INTERFACE, METADATA);
        if (res.isFailed()) throw new IOException("Failed to compileToMetadata. Reason: \n" + res.errors);
        CompilationResult result = CompilationResult.parse(res.output);
        return result.contracts.values().iterator().next();
    }

    public static List<AbiDefinition> loadContractDefinition(File abiFile) throws IOException {
        return Arrays.asList(ObjectMapperFactory.getObjectMapper().readValue(abiFile, AbiDefinition[].class));
    }

    public static List<AbiDefinition> loadContractDefinition(String abiBytes) throws IOException {
        return Arrays.asList(ObjectMapperFactory.getObjectMapper().readValue(abiBytes, AbiDefinition[].class));
    }

}
