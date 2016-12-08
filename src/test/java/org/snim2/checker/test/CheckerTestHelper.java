package org.snim2.checker.test;

import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;

import org.snim2.checker.ast.Formula;
import org.snim2.checker.parser.Parser;

public class CheckerTestHelper {

	public boolean checkFile(String filename) throws IOError, IOException {
		InputStream input = this.getClass().getResourceAsStream("/" + filename);
		return this.checkInputStream(input);
	}

	public boolean checkInputStream(InputStream input) throws IOError, IOException {
		Parser myParser = null;
		myParser = new Parser(input);
		input.close();
		Formula formula = myParser.getAST();
		formula = formula.removeImplications();
		formula = formula.toNnf();
		formula = formula.nnfToCnf();
		formula = formula.simplifyCnf();
		return org.snim2.checker.ast.True.VALUE == formula;
	}

}