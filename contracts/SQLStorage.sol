pragma solidity ^0.4.19;

contract SQLStorage {

    // architecture
    // columnsBundle a column is maximum 32 characters


    // metadata
    uint numberOfTables;
    // table index -> table name
    mapping(uint => string) tableNames;
    // table name -> count
    mapping(string => uint) numberOfColumns;
    // table name -> column index -> column name
    mapping(string => mapping(uint => string)) columnNames;
    // table name -> bool
    mapping(string => bool) tableExists;
    // table name -> column name -> bool
    mapping(string => mapping(string => bool)) columnExists;

    // actual storage
    // tablename -> column name -> index -> value
    mapping(string => mapping(string => mapping(uint => string))) tables;

    // tablename -> column name -> number of columns
    mapping(string => mapping(string => uint)) tableColumnCounts;


    function SQLStorage()
    public
    {

    }

    function createTable(string tableName, string columns)
    public
    {
        tableNames[numberOfTables] = tableName;
        numberOfTables += 1;
        uint numberOfCharacters = bytes(columns).length;
        uint j = 0;
        for (uint i = 32; i < numberOfCharacters; i += 32) {
            string memory columnName = substring(columns, j, i);
            j += 32;
            columnNames[tableName][numberOfColumns[tableName]] = columnName;
            numberOfColumns[tableName]+=1;
        }
    }

    function insert(string tableName, string column, string value)
    public
    {
        uint index = tableColumnCounts[tableName][column];
        tables[tableName][column][index] = value;
        tableColumnCounts[tableName][column] += 1;
    }

    function getValue(string tableName, string column, uint index)
    public returns (string)
    {
        return tables[tableName][column][index];
    }

    function getTableExists()
    public returns (bool)
    {
        return true;
    }

    // function copied from https://ethereum.stackexchange.com/questions/31457/substring-in-solidity
    function substring(string str, uint startIndex, uint endIndex)
    internal constant returns (string)
    {
        bytes memory strBytes = bytes(str);
        bytes memory result = new bytes(endIndex - startIndex);
        for (uint i = startIndex; i < endIndex; i++)
        result[i - startIndex] = strBytes[i];
        return string(result);
    }
}