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
import java.math.*;
import java.util.Arrays;
import java.util.regex.*;
import java.util.concurrent.CompletableFuture;

public class SQLParser {
    private static SQLStorage sqlStorage;

    public SQLParser(SQLStorage sqlS) {
        sqlStorage = sqlS;
    }

    public static void parseQuery(String query) throws Exception {
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

    private static void handleUPDATE(String query) throws Exception {
        // format: UPDATE <table name> SET <c1> = <v1>, <c2> = <v2>, ..., <cn> = <vn> WHERE <row index>
        int typeIndex = 6;
        int setIndex = query.indexOf("SET");
        int whereIndex = query.indexOf("WHERE");
        String inputs = query.substring(setIndex + 4, whereIndex - 1);
        String columns = "";
        String values = "";
        String inputsarray[] = inputs.split("=|,");
        int i = 0;
        for (i=0; i < inputsarray.length; i++) {
            columns = columns.concat(inputsarray[i]) + ",";
            i++;
            values = values.concat(inputsarray[i]) + ",";
        }

        String rowIndexstring = query.substring(whereIndex + 6);
        int rowIndex = Integer.parseInt(rowIndexstring);
        int numOfColumns = inputsarray.length / 2;

        columns = columns.replaceAll("\\s+","");
        values = values.replaceAll("\\s+","");
        String table_name = query.substring(typeIndex + 1, setIndex - 1);

        System.out.println(table_name);
        System.out.println(columns);
        System.out.println(values);
        //System.out.println(rowIndex);
        sqlStorage.update(table_name,BigInteger.valueOf(numOfColumns),BigInteger.valueOf(rowIndex),columns, values).send();
    }

    private static void handleSELECT(String query) throws Exception {
        // format: SELECT <columns1, columns2, ..., columnsn> FROM <table name>
        int typeIndex = 6;
        int rightIndex = query.indexOf("FROM") - 1;
        int tablenameIndex = query.indexOf("FROM");
        String table_name = query.substring(tablenameIndex + 5);

        String columns = query.substring(typeIndex + 1, rightIndex);
        columns = columns.replaceAll("\\s+", "");
        columns = columns.concat(",");

        int numOfColumns = 0;
        String colArr[] = columns.split(",");
        numOfColumns = colArr.length;

        int startRow = 0;
        BigInteger endRowb = sqlStorage.getRowCount(table_name).send();
        int endRow = endRowb.intValue();

        String rows[] = new String[endRow];
        endRow--;
        int i = 0;
        do {
            rows[i] = String.valueOf(sqlStorage.getSelect(table_name, BigInteger.valueOf(numOfColumns), BigInteger.valueOf(startRow), BigInteger.valueOf(endRow), columns).send());
            System.out.println(rows[i]);
            startRow++;
            i++;
        } while (startRow <= endRow);
        System.out.println();
    }

    private static void handleINSERT(String query) throws Exception {
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

        int numOfColumns = 0;
        String colArr[] = columns.split(",");
        numOfColumns = colArr.length;

        System.out.println(table_name);
        System.out.println(columns);

        for (i=0; i<numOfRows; i++) {
            rows[i] = rows[i].replaceAll("\\s+", "");
            rows[i] = rows[i].concat(",");
            System.out.println(rows[i]);
            sqlStorage.insert(table_name, BigInteger.valueOf(numOfColumns), columns, rows[i]).send();
        }
    }

    private static void handleCREATE(String query) throws Exception {
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
        sqlStorage.createTable(table_name, columns).send();
    }
}
