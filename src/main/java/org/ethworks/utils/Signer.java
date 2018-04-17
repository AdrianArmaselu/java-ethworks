package org.ethworks.utils;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.tx.ChainId;
import org.web3j.utils.Numeric;

public class Signer {
    public static String signTransaction(RawTransaction rawTransaction, byte chainId, Credentials credentials) {
        if (chainId > ChainId.NONE) {
            return Numeric.toHexString(TransactionEncoder.signMessage(rawTransaction, chainId, credentials));
        } else {
            return Numeric.toHexString(TransactionEncoder.signMessage(rawTransaction, credentials));
        }
    }
}
