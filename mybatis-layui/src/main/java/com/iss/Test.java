package com.iss;
import com.mybatis.code.CodeGenerator;
import com.mybatis.code.parser.DomParser;

public class Test {

	public static void main(String[] args) throws Exception {
		System.out.println("开始执行命令!");
		System.out.println("配置文件解析成功!");
		DomParser dom = new DomParser();
		dom.init();
		CodeGenerator.generate(DomParser.generatorConfigurations);// 生成代码
	}
}
