contract SQLStorage {

    // architecture
    // columnsBundle a column is maximum 32 characters


    // metadata
    int numberOfTables;
    // table index -> table name
    mapping(int => string) tableNames;
    // table name -> count
    mapping(string => int) numberOfColumns;
    // table name -> column index -> column name
    mapping(string => mapping(int => string)) columnNames;
    // table name -> bool
    mapping(string => bool) tableExists;
    // table name -> column name -> bool
    mapping(string => mapping(string => bool)) columnExists;

    // actual storage
    // tablename -> column name -> index -> value
    mapping(string => mapping(string => mapping(int => string))) tables;


    function SQLStorage() {
    }

    function createTable(string tableName, string columns){
        tableNames[numberOfColumns++] = tableName;
        int j = 0;
        for (int i = 32; i < columnsBundle.length; i += 32) {
            string columnName = substring(columnsBundle, j, i);
            j += 32;
            columnNames[tableName][numberOfColumns[tableName]++] = columnName;
        }
    }

    function insert(string tableName, string column, int value){
        tables[tableName][column] = value;
    }

    function getValue(string tableName, string column){
        return tables[tableName][column];
    }

    function getTableExists(){

    }

    // function copied from https://ethereum.stackexchange.com/questions/31457/substring-in-solidity
    function substring(string str, uint startIndex, uint endIndex) constant returns (string) {
        bytes memory strBytes = bytes(str);
        bytes memory result = new bytes(endIndex - startIndex);
        for (uint i = startIndex; i < endIndex; i++)
            result[i - startIndex] = strBytes[i];
        return string(result);
    }
}