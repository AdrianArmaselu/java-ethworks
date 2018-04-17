contract SimpleStorage {

    // tablename -> column -> value
    mapping ( string => (mapping (string => int))) tables;

    uint public value;

    function SimpleStorage() {
        value = 0;
    }

    function setValue(uint val) {
        value = val;
    }


value = v1;
    function insert(string tablename, string column, int value){
    tables[tablename][column] = value;

    }

function getValue(string tablename, string column){
return tables[tablename][column];

}
}