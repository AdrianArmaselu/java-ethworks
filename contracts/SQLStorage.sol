pragma solidity ^0.4.19;


// TODO: CHECK IF A TABLE ALREADY EXISTS BEFORE CREATING ONE
contract SQLStorage {

    bytes1 comma = 44;
    bytes1 recordSeparator = 124;

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
    mapping(string => mapping(string => uint)) columnIndex;
    // tablename -> column name -> index -> value
    mapping(string => mapping(string => mapping(uint => string))) tables;

    function SQLStorage()
    public
    {

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
        rowCount[tableName] = 0;
        tableExists[tableName] = true;
    }

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
        for (i = 0; i < charsLen; i++) {
            if (chars[i] == comma) {
                string memory value = substring(values, j, i);
                uint insertColumnIndex = indexes[k];
                columnName = columnNames[tableName][insertColumnIndex];
                uint rowIndex = rowCount[tableName];
                tables[tableName][columnName][rowIndex] = value;
                j = i + 1;
                k += 1;
            }

            if (chars[i] == recordSeparator){
                rowCount[tableName] += 1;
                k = 0;
                j += 1;
            }
        }
        rowCount[tableName] += 1;
    }

    // returns one row at a time
    function getSelect(string tableName, uint numberOfColumns, uint startRow, uint endRow, string columns)
    public returns (string)
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

        // first count how large our row will be
        k = 0;
        for (i = 0; i < numberOfColumns; i++) {
            j = indexes[i];
            columnName = columnNames[tableName][j];
            string memory value = tables[tableName][columnName][startRow];
            k += bytes(value).length;
        }

        bytes memory row = new bytes(k + numberOfColumns);
        k = 0;
        for (i = 0; i < numberOfColumns; i++) {
            j = indexes[i];
            columnName = columnNames[tableName][j];
            value = tables[tableName][columnName][startRow];
            chars = bytes(value);
            for (j = 0; j < chars.length; j++) {
                row[k] = chars[j];
                k += 1;
            }
            row[k] = ',';
            k += 1;
        }
        return string(row);

    }

    function update(string tableName, uint numberOfColumns, uint rowIndex, string columns, string values)
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

        j = 0;
        k = 0;
        chars = bytes(values);
        charsLen = chars.length;
        for (i = 0; i < charsLen; i++) {
            if (chars[i] == comma) {
                string memory value = substring(values, j, i);
                uint insertColumnIndex = indexes[k];
                columnName = columnNames[tableName][insertColumnIndex];
                tables[tableName][columnName][rowIndex] = value;
                j = i + 1;
                k += 1;
            }
        }

    }

    function getTableNumberCounter()
    public returns (uint)
    {
        return numberOfTables;
    }

    function getTableName(uint tableIndex)
    public returns (string)
    {
        return tableNames[tableIndex];
    }

    function getValue(string tableName, string column, uint rowNumber)
    public returns (string)
    {
        return tables[tableName][column][rowNumber];
    }

    function getColumnName(string tableName, uint columnIndex)
    public returns (string)
    {
        return columnNames[tableName][columnIndex];
    }

    function getTableExists()
    public returns (bool)
    {
        return true;
    }

    function getRowCount(string tableName)
    public returns (uint)
    {
        return rowCount[tableName];
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

    // function copied from http://cryptodir.blogspot.com/2016/03/solidity-concat-string.html
    function strConcat(string _a, string _b, string _c, string _d, string _e) internal returns (string){
        bytes memory _ba = bytes(_a);
        bytes memory _bb = bytes(_b);
        bytes memory _bc = bytes(_c);
        bytes memory _bd = bytes(_d);
        bytes memory _be = bytes(_e);
        string memory abcde = new string(_ba.length + _bb.length + _bc.length + _bd.length + _be.length);
        bytes memory babcde = bytes(abcde);
        uint k = 0;
        for (uint i = 0; i < _ba.length; i++) babcde[k++] = _ba[i];
        for (i = 0; i < _bb.length; i++) babcde[k++] = _bb[i];
        for (i = 0; i < _bc.length; i++) babcde[k++] = _bc[i];
        for (i = 0; i < _bd.length; i++) babcde[k++] = _bd[i];
        for (i = 0; i < _be.length; i++) babcde[k++] = _be[i];
        return string(babcde);
    }

    function strConcat(string _a, string _b, string _c, string _d) internal returns (string) {
        return strConcat(_a, _b, _c, _d, "");
    }

    function strConcat(string _a, string _b, string _c) internal returns (string) {
        return strConcat(_a, _b, _c, "", "");
    }

    function strConcat(string _a, string _b) internal returns (string) {
        return strConcat(_a, _b, "", "", "");
    }
}