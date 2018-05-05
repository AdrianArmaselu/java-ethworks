pragma solidity ^0.4.19;


// TODO: CHECK IF A TABLE ALREADY EXISTS BEFORE CREATING ONE
contract SQLStorage {

    bytes1 comma = 44;
    bytes1 recordSeparator = 30;

    // architecture
    // columnsBundle a column is maximum 32 characters


    // metadata
    uint numberOfTables;
    // table index -> table name
    mapping(uint => string) tableNames;
    // table name -> count
    mapping(string => uint) columnCount;
    // table name -> count
    mapping(string => uint) rowCount;
    // table name -> column index -> column name
    mapping(string => mapping(uint => string)) columnNames;
    // table name -> bool
    mapping(string => bool) tableExists;
    // table name -> column name -> bool
    mapping(string => mapping(string => bool)) columnExists;
    // tablename -> column name -> index
    mapping(string => mapping (string => uint)) columnIndex;
    // tablename -> column name -> index -> value
    mapping(string => mapping(string => mapping(uint => string))) tables;


    string myString;

    function SQLStorage()
    public
    {

    }

    function insertString(string someString)
    public
    {
        myString = someString;
    }

    function getInsertedString()
    public view returns (string){
        return myString;
    }

    function createTable(string tableName, string columns)
    public
    {
        tableNames[numberOfTables] = tableName;
        numberOfTables += 1;

        bytes memory characters = bytes(columns);
        uint charactersLen = characters.length;
        uint j = 0;

        for (uint i = 0; i < charactersLen; i += 1) {
            if (characters[i] == comma) {
                string memory columnName = substring(columns, j, i);
                columnNames[tableName][columnCount[tableName]] = columnName;
                columnIndex[tableName][columnName] = columnCount[tableName];
                columnCount[tableName] += 1;
                columnExists[tableName][columnName] = true;
                j = i + 1;
            }
        }
        tableExists[tableName] = true;
    }

    // need to process rows of values and not just one row
    // values will be of the form: v1,v2,...,vn,\30
    function insert(string tableName, uint numberOfColumns, string columns, string values)
    public
    {
        bytes memory chars = bytes(columns);
        uint charsLen = chars.length;

        // get the columns that are getting updated
        uint j = 0;
        uint k = 0;
        uint[] memory indexes = new uint[](numberOfColumns);
        for (uint i = 0; i < charsLen; i += 1) {
            if (chars[i] == comma) {
                string memory columnName = substring(columns, j, i);
                indexes[k] = columnIndex[tableName][columnName];
                k += 1;
                j = i + 1;
            }
        }

        // update table value and row count
        j = 0;
        k = 0;
        chars = bytes(values);
        charsLen = chars.length;
        for(i = 0 ; i < charsLen; i++){
            if (chars[i] == comma) {
                string memory value = substring(values, j, i);
                uint insertColumnIndex = indexes[k];
                columnName = columnNames[tableName][insertColumnIndex];
                uint rowIndex = rowCount[tableName];
                tables[tableName][columnName][rowIndex] = value;
                rowCount[tableName] += 1;
                j = i + 1;
                k += 1;
            }

            if (chars[i] == recordSeparator)
                k = 0;

        }
    }

    function getTableNumberCounter()
    public returns(uint)
    {
        return numberOfTables;
    }

    function getTableName(uint tableIndex)
    public returns(string)
    {
        return tableNames[tableIndex];
    }

    function getValue(string tableName, string column, uint rowNumber)
    public returns (string)
    {
        return tables[tableName][column][rowNumber];
    }

    function getColumnName(string tableName, uint columnIndex)
    public returns(string)
    {
        return columnNames[tableName][columnIndex];
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