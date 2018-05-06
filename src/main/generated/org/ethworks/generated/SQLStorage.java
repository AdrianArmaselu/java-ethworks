package org.ethworks.generated;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version null.
 */
public class SQLStorage extends Contract {
    private static final String BINARY = "6060604052602c7f0100000000000000000000000000000000000000000000000000000000000000026000806101000a81548160ff02191690837f010000000000000000000000000000000000000000000000000000000000000090040217905550601e7f010000000000000000000000000000000000000000000000000000000000000002600060016101000a81548160ff02191690837f01000000000000000000000000000000000000000000000000000000000000009004021790555034156100ca57600080fd5b612a97806100d96000396000f3006060604052600436106100a3576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806263bdac146100a85780631731d411146101445780632c918141146102785780633a31377e146103645780638146a348146103d5578063933ad2f7146104b45780639647d74a146105d6578063b5b18b1f14610603578063ee53100d146106f8578063fc8b4adf14610721575b600080fd5b34156100b357600080fd5b6100c960048080359060200190919050506107c1565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156101095780820151818401526020810190506100ee565b50505050905090810190601f1680156101365780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561014f57600080fd5b6101fd600480803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509190803590602001909190803590602001909190803590602001909190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509190505061087c565b6040518080602001828103825283818151815260200191508051906020019080838360005b8381101561023d578082015181840152602081019050610222565b50505050905090810190601f16801561026a5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561028357600080fd5b610362600480803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509190803590602001909190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509190803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919050506111d7565b005b341561036f57600080fd5b6103bf600480803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919050506119b3565b6040518082815260200191505060405180910390f35b34156103e057600080fd5b610439600480803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091908035906020019091905050611a28565b6040518080602001828103825283818151815260200191508051906020019080838360005b8381101561047957808201518184015260208101905061045e565b50505050905090810190601f1680156104a65780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34156104bf57600080fd5b61055b600480803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509190803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091908035906020019091905050611b4d565b6040518080602001828103825283818151815260200191508051906020019080838360005b8381101561059b578082015181840152602081019050610580565b50505050905090810190601f1680156105c85780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34156105e157600080fd5b6105e9611cdc565b604051808215151515815260200191505060405180910390f35b341561060e57600080fd5b6106f6600480803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509190803590602001909190803590602001909190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509190803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091905050611ce5565b005b341561070357600080fd5b61070b612281565b6040518082815260200191505060405180910390f35b341561072c57600080fd5b6107bf600480803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509190505061228b565b005b6107c961298a565b600260008381526020019081526020016000208054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156108705780601f1061084557610100808354040283529160200191610870565b820191906000526020600020905b81548152906001019060200180831161085357829003601f168201915b50505050509050919050565b61088461298a565b61088c61299e565b60008060006108996129b2565b60006108a361298a565b6108ab61298a565b6108b361299e565b8a98508851975060009650600095508d6040518059106108d05750595b90808252806020026020018201604052509450600093505b87841015610ad7576000809054906101000a90047f0100000000000000000000000000000000000000000000000000000000000000027effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916898581518110151561094e57fe5b9060200101517f010000000000000000000000000000000000000000000000000000000000000090047f0100000000000000000000000000000000000000000000000000000000000000027effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff19161415610acc576109cc8b8886612887565b925060088f6040518082805190602001908083835b602083101515610a0657805182526020820191506020810190506020830392506109e1565b6001836020036101000a0380198251168184511680821785525050505050509050019150509081526020016040518091039020836040518082805190602001908083835b602083101515610a6f5780518252602082019150602081019050602083039250610a4a565b6001836020036101000a0380198251168184511680821785525050505050509050019150509081526020016040518091039020548587815181101515610ab157fe5b90602001906020020181815250506001860195506001840196505b6001840193506108e8565b60009550600093505b8d841015610dac578484815181101515610af657fe5b90602001906020020151965060058f6040518082805190602001908083835b602083101515610b3a5780518252602082019150602081019050602083039250610b15565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051809103902060008881526020019081526020016000208054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610c125780601f10610be757610100808354040283529160200191610c12565b820191906000526020600020905b815481529060010190602001808311610bf557829003601f168201915b5050505050925060098f6040518082805190602001908083835b602083101515610c515780518252602082019150602081019050602083039250610c2c565b6001836020036101000a0380198251168184511680821785525050505050509050019150509081526020016040518091039020836040518082805190602001908083835b602083101515610cba5780518252602082019150602081019050602083039250610c95565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051809103902060008e81526020019081526020016000208054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610d925780601f10610d6757610100808354040283529160200191610d92565b820191906000526020600020905b815481529060010190602001808311610d7557829003601f168201915b505050505091508151860195508380600101945050610ae0565b8d8601604051805910610dbc5750595b9080825280601f01601f1916602001820160405250905060009550600093505b8d8410156111c2578484815181101515610df257fe5b90602001906020020151965060058f6040518082805190602001908083835b602083101515610e365780518252602082019150602081019050602083039250610e11565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051809103902060008881526020019081526020016000208054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610f0e5780601f10610ee357610100808354040283529160200191610f0e565b820191906000526020600020905b815481529060010190602001808311610ef157829003601f168201915b5050505050925060098f6040518082805190602001908083835b602083101515610f4d5780518252602082019150602081019050602083039250610f28565b6001836020036101000a0380198251168184511680821785525050505050509050019150509081526020016040518091039020836040518082805190602001908083835b602083101515610fb65780518252602082019150602081019050602083039250610f91565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051809103902060008e81526020019081526020016000208054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561108e5780601f106110635761010080835404028352916020019161108e565b820191906000526020600020905b81548152906001019060200180831161107157829003601f168201915b50505050509150819850600096505b88518710156111505788878151811015156110b457fe5b9060200101517f010000000000000000000000000000000000000000000000000000000000000090047f010000000000000000000000000000000000000000000000000000000000000002818781518110151561110d57fe5b9060200101907effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916908160001a905350600186019550868060010197505061109d565b7f2c00000000000000000000000000000000000000000000000000000000000000818781518110151561117f57fe5b9060200101907effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916908160001a9053506001860195508380600101945050610ddc565b80995050505050505050505095945050505050565b6111df61299e565b60008060006111ec6129b2565b60006111f661298a565b6111fe61298a565b6000808b99508951985060009750600096508c60405180591061121e5750595b90808252806020026020018201604052509550600094505b88851015611425576000809054906101000a90047f0100000000000000000000000000000000000000000000000000000000000000027effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff19168a8681518110151561129c57fe5b9060200101517f010000000000000000000000000000000000000000000000000000000000000090047f0100000000000000000000000000000000000000000000000000000000000000027effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916141561141a5761131a8c8987612887565b935060088e6040518082805190602001908083835b602083101515611354578051825260208201915060208101905060208303925061132f565b6001836020036101000a0380198251168184511680821785525050505050509050019150509081526020016040518091039020846040518082805190602001908083835b6020831015156113bd5780518252602082019150602081019050602083039250611398565b6001836020036101000a03801982511681845116808217855250505050505090500191505090815260200160405180910390205486888151811015156113ff57fe5b90602001906020020181815250506001870196506001850197505b600185019450611236565b60009750600096508a995089519850600094505b88851015611929576000809054906101000a90047f0100000000000000000000000000000000000000000000000000000000000000027effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff19168a8681518110151561149f57fe5b9060200101517f010000000000000000000000000000000000000000000000000000000000000090047f0100000000000000000000000000000000000000000000000000000000000000027effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff191614156117c55761151d8b8987612887565b9250858781518110151561152d57fe5b90602001906020020151915060058e6040518082805190602001908083835b602083101515611571578051825260208201915060208101905060208303925061154c565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051809103902060008381526020019081526020016000208054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156116495780601f1061161e57610100808354040283529160200191611649565b820191906000526020600020905b81548152906001019060200180831161162c57829003601f168201915b5050505050935060048e6040518082805190602001908083835b6020831015156116885780518252602082019150602081019050602083039250611663565b6001836020036101000a03801982511681845116808217855250505050505090500191505090815260200160405180910390205490508260098f6040518082805190602001908083835b6020831015156116f757805182526020820191506020810190506020830392506116d2565b6001836020036101000a0380198251168184511680821785525050505050509050019150509081526020016040518091039020856040518082805190602001908083835b602083101515611760578051825260208201915060208101905060208303925061173b565b6001836020036101000a0380198251168184511680821785525050505050509050019150509081526020016040518091039020600083815260200190815260200160002090805190602001906117b79291906129c6565b506001850197506001870196505b600060019054906101000a90047f0100000000000000000000000000000000000000000000000000000000000000027effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff19168a8681518110151561182457fe5b9060200101517f010000000000000000000000000000000000000000000000000000000000000090047f0100000000000000000000000000000000000000000000000000000000000000027effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916141561191c57600160048f6040518082805190602001908083835b6020831015156118d157805182526020820191506020810190506020830392506118ac565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051809103902060008282540192505081905550600096506001880197505b8480600101955050611439565b600160048f6040518082805190602001908083835b602083101515611963578051825260208201915060208101905060208303925061193e565b6001836020036101000a0380198251168184511680821785525050505050509050019150509081526020016040518091039020600082825401925050819055505050505050505050505050505050565b60006004826040518082805190602001908083835b6020831015156119ed57805182526020820191506020810190506020830392506119c8565b6001836020036101000a0380198251168184511680821785525050505050509050019150509081526020016040518091039020549050919050565b611a3061298a565b6005836040518082805190602001908083835b602083101515611a685780518252602082019150602081019050602083039250611a43565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051809103902060008381526020019081526020016000208054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015611b405780601f10611b1557610100808354040283529160200191611b40565b820191906000526020600020905b815481529060010190602001808311611b2357829003601f168201915b5050505050905092915050565b611b5561298a565b6009846040518082805190602001908083835b602083101515611b8d5780518252602082019150602081019050602083039250611b68565b6001836020036101000a0380198251168184511680821785525050505050509050019150509081526020016040518091039020836040518082805190602001908083835b602083101515611bf65780518252602082019150602081019050602083039250611bd1565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051809103902060008381526020019081526020016000208054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015611cce5780601f10611ca357610100808354040283529160200191611cce565b820191906000526020600020905b815481529060010190602001808311611cb157829003601f168201915b505050505090509392505050565b60006001905090565b611ced61299e565b6000806000611cfa6129b2565b6000611d0461298a565b611d0c61298a565b60008a98508851975060009650600095508c604051805910611d2b5750595b90808252806020026020018201604052509450600093505b87841015611f32576000809054906101000a90047f0100000000000000000000000000000000000000000000000000000000000000027effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff19168985815181101515611da957fe5b9060200101517f010000000000000000000000000000000000000000000000000000000000000090047f0100000000000000000000000000000000000000000000000000000000000000027effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff19161415611f2757611e278b8886612887565b925060088e6040518082805190602001908083835b602083101515611e615780518252602082019150602081019050602083039250611e3c565b6001836020036101000a0380198251168184511680821785525050505050509050019150509081526020016040518091039020836040518082805190602001908083835b602083101515611eca5780518252602082019150602081019050602083039250611ea5565b6001836020036101000a0380198251168184511680821785525050505050509050019150509081526020016040518091039020548587815181101515611f0c57fe5b90602001906020020181815250506001860195506001840196505b600184019350611d43565b600096506000955089985088519750600093505b87841015612271576000809054906101000a90047f0100000000000000000000000000000000000000000000000000000000000000027effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff19168985815181101515611fac57fe5b9060200101517f010000000000000000000000000000000000000000000000000000000000000090047f0100000000000000000000000000000000000000000000000000000000000000027effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff191614156122645761202a8a8886612887565b9150848681518110151561203a57fe5b90602001906020020151905060058e6040518082805190602001908083835b60208310151561207e5780518252602082019150602081019050602083039250612059565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051809103902060008281526020019081526020016000208054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156121565780601f1061212b57610100808354040283529160200191612156565b820191906000526020600020905b81548152906001019060200180831161213957829003601f168201915b505050505092508160098f6040518082805190602001908083835b6020831015156121965780518252602082019150602081019050602083039250612171565b6001836020036101000a0380198251168184511680821785525050505050509050019150509081526020016040518091039020846040518082805190602001908083835b6020831015156121ff57805182526020820191506020810190506020830392506121da565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051809103902060008e815260200190815260200160002090805190602001906122569291906129c6565b506001840196506001860195505b8380600101945050611f46565b5050505050505050505050505050565b6000600154905090565b61229361299e565b60008060006122a061298a565b8660026000600154815260200190815260200160002090805190602001906122c99291906129c6565b50600180600082825401925050819055508594508451935060009250600091505b83821015612789576000809054906101000a90047f0100000000000000000000000000000000000000000000000000000000000000027effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916858381518110151561235057fe5b9060200101517f010000000000000000000000000000000000000000000000000000000000000090047f0100000000000000000000000000000000000000000000000000000000000000027effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916141561277e576123ce868484612887565b9050806005886040518082805190602001908083835b60208310151561240957805182526020820191506020810190506020830392506123e4565b6001836020036101000a0380198251168184511680821785525050505050509050019150509081526020016040518091039020600060038a6040518082805190602001908083835b6020831015156124765780518252602082019150602081019050602083039250612451565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051809103902054815260200190815260200160002090805190602001906124cb9291906129c6565b506003876040518082805190602001908083835b60208310151561250457805182526020820191506020810190506020830392506124df565b6001836020036101000a0380198251168184511680821785525050505050509050019150509081526020016040518091039020546008886040518082805190602001908083835b602083101515612570578051825260208201915060208101905060208303925061254b565b6001836020036101000a0380198251168184511680821785525050505050509050019150509081526020016040518091039020826040518082805190602001908083835b6020831015156125d957805182526020820191506020810190506020830392506125b4565b6001836020036101000a03801982511681845116808217855250505050505090500191505090815260200160405180910390208190555060016003886040518082805190602001908083835b60208310151561264a5780518252602082019150602081019050602083039250612625565b6001836020036101000a03801982511681845116808217855250505050505090500191505090815260200160405180910390206000828254019250508190555060016007886040518082805190602001908083835b6020831015156126c4578051825260208201915060208101905060208303925061269f565b6001836020036101000a0380198251168184511680821785525050505050509050019150509081526020016040518091039020826040518082805190602001908083835b60208310151561272d5780518252602082019150602081019050602083039250612708565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051809103902060006101000a81548160ff0219169083151502179055506001820192505b6001820191506122ea565b60006004886040518082805190602001908083835b6020831015156127c3578051825260208201915060208101905060208303925061279e565b6001836020036101000a03801982511681845116808217855250505050505090500191505090815260200160405180910390208190555060016006886040518082805190602001908083835b602083101515612834578051825260208201915060208101905060208303925061280f565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051809103902060006101000a81548160ff02191690831515021790555050505050505050565b61288f61298a565b61289761299e565b61289f61299e565b60008692508585036040518059106128b45750595b9080825280601f01601f191660200182016040525091508590505b8481101561297d5782818151811015156128e557fe5b9060200101517f010000000000000000000000000000000000000000000000000000000000000090047f0100000000000000000000000000000000000000000000000000000000000000028287830381518110151561294057fe5b9060200101907effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916908160001a90535080806001019150506128cf565b8193505050509392505050565b602060405190810160405280600081525090565b602060405190810160405280600081525090565b602060405190810160405280600081525090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10612a0757805160ff1916838001178555612a35565b82800160010185558215612a35579182015b82811115612a34578251825591602001919060010190612a19565b5b509050612a429190612a46565b5090565b612a6891905b80821115612a64576000816000905550600101612a4c565b5090565b905600a165627a7a72305820746dfffd46d41fa5ba5ddd5f0d9df64dff4627c1f0b68deccd70915aa11c0d450029";

    protected SQLStorage(String contractAddress, Web3j web3j, Credentials accountCredentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, accountCredentials, gasPrice, gasLimit);
        try {
                    this.contractAddress = send(null, BINARY, BigInteger.ZERO, gasPrice, gasLimit).getContractAddress();
                } catch (IOException | TransactionException e) {
                    e.printStackTrace();
                }
    }

    protected SQLStorage(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
        try {
                    this.contractAddress = send(null, BINARY, BigInteger.ZERO, gasPrice, gasLimit).getContractAddress();
                } catch (IOException | TransactionException e) {
                    e.printStackTrace();
                }
    }

    public RemoteCall<String> getTableName(BigInteger tableIndex) {
        Function function = new Function("getTableName", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tableIndex)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> getSelect(String tableName, BigInteger numberOfColumns, BigInteger startRow, BigInteger endRow, String columns) {
        Function function = new Function("getSelect", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(tableName), 
                new org.web3j.abi.datatypes.generated.Uint256(numberOfColumns), 
                new org.web3j.abi.datatypes.generated.Uint256(startRow), 
                new org.web3j.abi.datatypes.generated.Uint256(endRow), 
                new org.web3j.abi.datatypes.Utf8String(columns)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> insert(String tableName, BigInteger numberOfColumns, String columns, String values) {
        Function function = new Function(
                "insert", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(tableName), 
                new org.web3j.abi.datatypes.generated.Uint256(numberOfColumns), 
                new org.web3j.abi.datatypes.Utf8String(columns), 
                new org.web3j.abi.datatypes.Utf8String(values)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> getRowCount(String tableName) {
        Function function = new Function("getRowCount", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(tableName)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> getColumnName(String tableName, BigInteger columnIndex) {
        Function function = new Function("getColumnName", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(tableName), 
                new org.web3j.abi.datatypes.generated.Uint256(columnIndex)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> getValue(String tableName, String column, BigInteger rowNumber) {
        Function function = new Function("getValue", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(tableName), 
                new org.web3j.abi.datatypes.Utf8String(column), 
                new org.web3j.abi.datatypes.generated.Uint256(rowNumber)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<Boolean> getTableExists() {
        Function function = new Function("getTableExists", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<TransactionReceipt> update(String tableName, BigInteger numberOfColumns, BigInteger rowIndex, String columns, String values) {
        Function function = new Function(
                "update", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(tableName), 
                new org.web3j.abi.datatypes.generated.Uint256(numberOfColumns), 
                new org.web3j.abi.datatypes.generated.Uint256(rowIndex), 
                new org.web3j.abi.datatypes.Utf8String(columns), 
                new org.web3j.abi.datatypes.Utf8String(values)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> getTableNumberCounter() {
        Function function = new Function("getTableNumberCounter", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> createTable(String tableName, String columns) {
        Function function = new Function(
                "createTable", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(tableName), 
                new org.web3j.abi.datatypes.Utf8String(columns)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<SQLStorage> deploy(Web3j web3j, Credentials accountCredentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SQLStorage.class, web3j, accountCredentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<SQLStorage> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SQLStorage.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static SQLStorage load(String contractAddress, Web3j web3j, Credentials accountCredentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SQLStorage(contractAddress, web3j, accountCredentials, gasPrice, gasLimit);
    }

    public static SQLStorage load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SQLStorage(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
