package org.ethsql;

import org.apache.commons.lang3.StringUtils;
import org.ethworks.client.ClientConfiguration;
import org.ethworks.client.EthAccountClient;
import org.ethworks.generated.SQLStorage;

import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ClientConfiguration clientConfiguration = new ClientConfiguration("src/main/resources/genache.conf");
        EthAccountClient ethAccountClient = new EthAccountClient(clientConfiguration);
        SQLStorage sqlStorage = ethAccountClient.initializeContract(SQLStorage.class, null);

        sqlStorage.createTable("table1", "c1" + StringUtils.repeat(" ", 32 - "c1".length()));

    }
}
