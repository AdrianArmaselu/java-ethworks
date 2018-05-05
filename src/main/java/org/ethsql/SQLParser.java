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
import java.util.regex.*;
import java.util.concurrent.CompletableFuture;

public class SQLParser {
    private static SQLStorage sqlStorage;

    public SQLParser(SQLStorage sqlS) {
        sqlStorage = sqlS;
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
        // format: UPDATE <table name> SET (<c1> = <v1>, <c2> = <v2>, ..., <cn> = <vn>)
        int typeIndex = 6;
        int setIndex = query.indexOf("SET");



        String table_name = query.substring(typeIndex + 1, setIndex - 1);
    }

    private static void handleSELECT(String query) {
        // format: SELECT * FROM <table name>
        //         SELECT (<c1>, <c2>, ..., <cn>) FROM <table name>
        int typeIndex = 6;

        if (query.charAt(7) == '*') {
            int tablenameIndex = 14;
            String table_name = query.substring(tablenameIndex + 1);
            System.out.println(table_name);
            String columns; // = get columns from table_name
            // function that returns data in specified columns
        }
        else {
            int leftparencolumnIndex = query.indexOf("(");
            int rightparencolumnIndex = query.indexOf(")");
            int tablenameIndex = query.indexOf("FROM") + 4;
            String table_name = query.substring(tablenameIndex + 1);
            String columns = query.substring(leftparencolumnIndex+1, rightparencolumnIndex);
            // function that returns data in the specified columns
        }

    }

    private static void handleINSERT(String query) {
        // format: INSERT INTO <table name> (<c1>, <c2>, ..., <cn>) VALUES (<v11>, <v12>, ..., <vin>)(<v21>, <v22>, ..., <vjn>)
        int typeIndex = 11;
        int leftparencolumnIndex = query.indexOf("(");
        int rightparencolumnIndex = query.indexOf(")");
        int valueIndex = query.indexOf("VALUES") + 6;
        String allValues = query.substring(valueIndex+1);

        Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(allValues);
        int numOfRows = 0;

        // count number of rows
        while(m.find()) { numOfRows++; }

        // initialize rows string
        String rows[]= new String[numOfRows];

        // restart matcher from 0
        m.find(0);
        rows[0] = m.group(1);
        int i = 1;
        while(m.find()) {
            rows[i] = m.group(1);
            i++;
        }

        String table_name = query.substring(typeIndex + 1, leftparencolumnIndex - 1);
        String columns = query.substring(leftparencolumnIndex+1, rightparencolumnIndex);
        columns = columns.replaceAll("\\s+","");
        columns = columns.concat(",");

        System.out.println(table_name);
        System.out.println(columns);

        for (i=0; i<numOfRows; i++) {
            rows[i] = rows[i].replaceAll("\\s+", "");
            rows[i] = rows[i].concat(",");
            System.out.println(rows[i]);
            sqlStorage.insert(table_name, BigInteger.ONE, columns, rows[i]);
        }
    }

    private static void handleCREATE(String query) {
        // format: CREATE TABLE <table name> (<c1>, <c2>, ..., <cn>)
        int typeIndex = 12;
        int leftparenIndex = query.indexOf("(");
        int rightparenIndex = query.indexOf(")");
        String table_name = query.substring(typeIndex + 1, leftparenIndex - 1);
        String columns = query.substring(leftparenIndex + 1, rightparenIndex);

        columns = columns.replaceAll("\\s+","");
        columns = columns.concat(",");

        System.out.println(table_name);
        System.out.println(columns);
        sqlStorage.createTable(table_name, columns);
    }
}
