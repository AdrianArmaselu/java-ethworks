package org.ethsql;

import io.netty.util.concurrent.CompleteFuture;
import org.apache.commons.lang3.StringUtils;
import org.ethworks.client.ClientConfiguration;
import org.ethworks.client.EthAccountClient;
import org.ethworks.generated.SQLStorage;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.ClientTransactionManager;
import org.web3j.tx.TransactionManager;

import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class SQLParser {
    public void SQLParser(SQLStorage sqlStorage) {

    }

    public static void parseQuery(String query) {
        int typeIndex = query.indexOf(" ");
        String type = query.substring(0, typeIndex);
        type = type.toUpperCase();

        switch (type) {
            case "CREATE":
                handleCREATE(query);
                break;
            case "INSERT":
                handleINSERT(query);
                break;
            case "SELECT":
                handleSELECT(query);
                break;
            case "UPDATE":
                handleUPDATE(query);
                break;
        }
    }

    private static void handleUPDATE(String query) {
        // format: UPDATE <table name> SET <c1> = <v1>, <c2> = <v2>, ..., <cn> = <vn>

    }

    private static void handleSELECT(String query) {
        // format: SELECT * FROM <table name>
        //         SELECT (<c1>, <c2>, ..., <cn>) FROM <table name>

    }

    private static void handleINSERT(String query) {
        // format: INSERT INTO <table name> (<c1>, <c2>, ..., <cn>) VALUES (<v11>, <v12>, ..., <vin>)(<v21>, <v22>, ..., <vjn>)
        int typeIndex = query.indexOf("INSERT INTO ");
        int leftparencolumnIndex = query.indexOf("(");
        int rightparencolumnIndex = query.indexOf(")");
        int leftparenvalueIndex = query.indexOf("(", query.indexOf("(") + 1);
        int rightparenvalueIndex = query.indexOf(")", query.indexOf(")") + 1);

        String table_name = query.substring(typeIndex + 1, leftparencolumnIndex - 2);
        String columns = query.substring(leftparencolumnIndex+1, rightparencolumnIndex - 1);
        String values = query.substring(leftparenvalueIndex+1, rightparenvalueIndex-1);
        sqlStorage.insertTable(table_name,columns,values);
    }

    private static void handleCREATE(String query) {
        // format: CREATE TABLE <table name> (<c1>, <c2>, ..., <cn>)
        int typeIndex = query.indexOf("CREATE TABLE ");
        int leftparenIndex = query.indexOf("(");
        int rightparenIndex = query.indexOf(")");
        String table_name = query.substring(typeIndex + 1, leftparenIndex - 2);
        String columns = query.substring(leftparenIndex + 1, rightparenIndex - 1);
        sqlStorage.createTable(table_name, columns);
    }
}
