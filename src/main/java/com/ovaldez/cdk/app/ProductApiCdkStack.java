package com.ovaldez.cdk.app;

import com.ovaldez.cdk.constructor.Apigateway;
import com.ovaldez.cdk.constructor.DataBases;
import com.ovaldez.cdk.constructor.Lambdas;

import software.amazon.awscdk.Stack;
import software.constructs.Construct;

public class ProductApiCdkStack extends Stack {
    public ProductApiCdkStack(final Construct scope, final String id) {
        super(scope, id);
        DataBases dbs = new DataBases(this, "Database");
        Lambdas lambdas = new Lambdas(this, "Lambdas", dbs.getProductTable());
        new Apigateway(this, "ApiGateway", lambdas.getProdFunction());
    }

}
