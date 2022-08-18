package com.ovaldez.cdk.constructor;

import org.jetbrains.annotations.NotNull;

import software.amazon.awscdk.services.apigateway.LambdaRestApi;
import software.amazon.awscdk.services.apigateway.Resource;
import software.amazon.awscdk.services.lambda.Function;
import software.constructs.Construct;

public class Apigateway extends Construct{

	public Apigateway(@NotNull Construct scope, @NotNull String id, Function prodFunction) {
		super(scope, id);
		createProductApi(prodFunction);
	}
	
	private void createProductApi(Function productFunction) {
		LambdaRestApi prodLambda = LambdaRestApi.Builder.create(this, "productApi")
				.restApiName("Product Service")
				.handler(productFunction)
				.proxy(false)
				.build();
		Resource product = prodLambda.getRoot().addResource("products");
		product.addMethod("GET");
		
		Resource singleProd = product.addResource("{id}");
		singleProd.addMethod("GET");
		//product.addMethod("POST");
				
	}

}
