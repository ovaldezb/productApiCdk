package com.ovaldez.cdk.constructor;

import org.jetbrains.annotations.NotNull;

import software.amazon.awscdk.RemovalPolicy;
import software.amazon.awscdk.services.dynamodb.Attribute;
import software.amazon.awscdk.services.dynamodb.AttributeType;
import software.amazon.awscdk.services.dynamodb.BillingMode;
import software.amazon.awscdk.services.dynamodb.Table;
import software.constructs.Construct;

public class DataBases extends Construct{
	
	private Table productTable;

	public DataBases(@NotNull Construct scope, @NotNull String id) {
		super(scope, id);
		this.productTable = createProductTable();
	}
	
	private Table createProductTable() {
		return Table.Builder.create(this, "Products")
				.billingMode(BillingMode.PAY_PER_REQUEST)
				.partitionKey(Attribute.builder().name("id").type(AttributeType.NUMBER).build())
				.removalPolicy(RemovalPolicy.DESTROY)
				.tableName("Products")
				.build();
		
	}

	public Table getProductTable() {
		return productTable;
	}

}
