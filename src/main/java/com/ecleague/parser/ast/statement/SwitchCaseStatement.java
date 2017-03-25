package com.ecleague.parser.ast.statement;

import com.ecleague.parser.ast.csharp.KeyWord;
import com.ecleague.parser.ast.csharp.Operators;
import com.ecleague.parser.ast.exception.ParseSyntaxException;
import com.ecleague.parser.ast.expression.Expression;
import com.ecleague.parser.ast.expression.ExpressionImpl;
import com.ecleague.parser.ast.util.Util;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by coraline on 17/3/24.
 */
public class SwitchCaseStatement implements Statement {
    private Expression switchExpression;

    private List<CaseBlock> caseBlockList = new ArrayList<>();

    public Expression getExpression() {
        return switchExpression;
    }

    public void setExpression(Expression expression) {
        this.switchExpression = expression;
    }

    @Override
    public String parse(String sourceCode) {
        String temp = StringUtils.trimToEmpty(sourceCode);

        if (!temp.startsWith(KeyWord.SWITCH))
            throw new ParseSyntaxException(this, sourceCode);

        temp = Util.trimTarget(temp, KeyWord.SWITCH);
        temp = Util.trimTarget(temp, Operators.LEFT_BRACKET);

        switchExpression = new ExpressionImpl();
        temp = switchExpression.parse(temp);

        temp = Util.trimTarget(temp, Operators.RIGHT_BRACKET);
        temp = Util.trimTarget(temp, Operators.LEFT_BRACE);
        while(temp.startsWith(KeyWord.CASE)){
            CaseBlock caseBlock = new CaseBlock();
            Expression expression = new ExpressionImpl();
            temp = Util.trimTarget(temp, KeyWord.CASE);
            temp = expression.parse(temp);
            temp = Util.trimTarget(temp, Operators.COLON);
            if(!temp.startsWith(KeyWord.CASE)){
                List<Statement> statementList = new ArrayList<>();
                temp = StatementFactory.processBlock(temp, statementList);
            }
            else{
                caseBlock.setExpression(expression);
                caseBlockList.add(caseBlock);
            }
        }

        if(temp.startsWith(KeyWord.DEFAULT)){
            temp = Util.trimTarget(temp, KeyWord.DEFAULT);
            temp = Util.trimTarget(temp, Operators.COLON);
            List<Statement> statementList = new ArrayList<>();
            temp = StatementFactory.processBlock(temp, statementList);
        }

        return Util.trimTarget(temp, Operators.RIGHT_BRACKET);

    }


    public static class CaseBlock{
        private Expression expression;
        private List<Statement> statementList;

        public Expression getExpression() {
            return expression;
        }

        public void setExpression(Expression expression) {
            this.expression = expression;
        }

        public List<Statement> getStatementList() {
            return statementList;
        }

        public void setStatementList(List<Statement> statementList) {
            this.statementList = statementList;
        }
    }
}
