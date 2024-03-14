package com.giantLink.Hiring.recrutementservice.exceptions;

public class ResourceNotFound extends RuntimeException{

    public ResourceNotFound(String message){
        super(message);
    }
    public ResourceNotFound(String entity, String field, String value){
        super(String.format("%s Not Found With %s:%s",entity,field,value));
    }
    public ResourceNotFound(String entity, String field1, String value1,String field2,String value2){
        super(String.format("%s Not Found With %s:%s And %s:%s",entity,field1,value1,field2,value2));
    }
}