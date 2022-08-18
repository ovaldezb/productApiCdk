package com.ovaldez.cdk.constructor;



import java.util.HashMap;
import java.util.Map;

import org.jetbrains.annotations.NotNull;

import software.amazon.awscdk.Duration;
import software.amazon.awscdk.services.dynamodb.Table;
import software.amazon.awscdk.services.lambda.Code;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.lambda.Runtime;
import software.constructs.Construct;

public class Lambdas extends Construct{
	
	private Function prodFunction;

	public Lambdas(@NotNull Construct scope, @NotNull String id, Table prodTable) {
		super(scope, id);
		this.prodFunction = createProductFunction(prodTable);
	}
	
	private Function createProductFunction(Table prodTable) {
		Map<String, String> env = new HashMap<String, String>();
		env.put("PRIMARY_KEY", "id");
		env.put("DYNAMODB_TABLE_NAME", prodTable.getTableName());
		Function fn = Function.Builder.create(this, "MyFunc")
				.memorySize(512)
				.timeout(Duration.seconds(20))
				.runtime(Runtime.JAVA_8)
				.code(Code.fromAsset("/Users/macbookpro/Documents/workspace-aws/lambda-test/target/lambda-test-1.0.0.jar"))
				.handler("com.ovaldez.aws.crud.lambda.api.ProductLambdaHandler::handleRequest")
				.environment(env)
				.build();
		prodTable.grantReadWriteData(fn);
		return fn;
	}

	public Function getProdFunction() {
		return prodFunction;
	}
	
	

}
