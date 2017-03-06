package com.ecleague.parser.ast;

import java.util.List;

/**
 * Author: EthanPark <br/>
 * Date: 2017/3/5<br/>
 * Email: byp5303628@hotmail.com
 */
public class Function implements SourceParser {
   private String returnType;
   private boolean staticFunction;
   private List<ArgType> argTypes;

   //region Function body
   private List<Statement> statements;
   private List<ArgType> functionParams;

   private ReturnStatement returnStatement;
   //endregion

   public String getReturnType() {
      return returnType;
   }

   public void setReturnType(String returnType) {
      this.returnType = returnType;
   }

   public boolean isStaticFunction() {
      return staticFunction;
   }

   public void setStaticFunction(boolean staticFunction) {
      this.staticFunction = staticFunction;
   }

   public List<ArgType> getArgTypes() {
      return argTypes;
   }

   public void setArgTypes(List<ArgType> argTypes) {
      this.argTypes = argTypes;
   }

   public List<Statement> getStatements() {
      return statements;
   }

   public void setStatements(List<Statement> statements) {
      this.statements = statements;
   }

   public List<ArgType> getFunctionParams() {
      return functionParams;
   }

   public void setFunctionParams(List<ArgType> functionParams) {
      this.functionParams = functionParams;
   }

   public ReturnStatement getReturnStatement() {
      return returnStatement;
   }

   public void setReturnStatement(ReturnStatement returnStatement) {
      this.returnStatement = returnStatement;
   }

   /**
    * Take the source code as the param, parse and generate ast object.
    *
    * @param sourceCode
    */
   @Override
   public String parse(String sourceCode) {
      return null;
   }
}
