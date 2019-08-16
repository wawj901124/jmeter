package com.jmeter_test;


import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

/*
 * Jmeter自定义脚本测试类
 * 该脚本功能为发送并输出指定的字符串
 */

public class jmeter_test extends AbstractJavaSamplerClient{

	/*
	 * 输出到Jmeter控制台的日志类
	 * 需要引用Jmeter lib目录下的logkit-2.0.jar*/
	private SampleResult results;   //运行结果
	private String testStr;   //jmeter控制台输入哦参数

	/*
	 * 设置传入的参数，可以设置多个，已设置的参数会显示到Jmeter的参数列表中。
	 * */
	public Arguments getDefaultparameters() {
		Arguments params = new Arguments();
		/*
		 * 定义一个参数，显示到Jmeter的参数列表中，
		 * 第一个参数为参数默认的显示名称，
		 * 第二个参数为默认值*/

		params.addArgument("testStr","");
		return params;

	}

	/*
	 * 初始化方法，实际运行时每个线程仅执行一次，在测试方法运行前执行，
	 * 类似于LoadRunner中的init方法
	 * */
	public void setupTest(JavaSamplerContext arg0) {
		results = new SampleResult();
		testStr = arg0.getParameter("testStr", "zxw");   //初始化默认的参数

		if(testStr !=null && testStr.length()>0) {
			results.setSamplerData(testStr);
		}
	}

	/*
	 * 测试执行的循环体，根据线程数和循环次数的不同可执行多次*/
	public SampleResult runTest(JavaSamplerContext arg0) {

		//定义一个事务，表示这是事务的起始点，类似于LoadRunner的lr.start_transaction
		//result.sampleStart();
		//定义一个事务，表示这是事务的结束点，类似于LoadRunner的lr.end_transaction
		//result.sampleEnd();
		if(testStr.length() < 0) {
			//用于设置运行结果的成功或失败，如果是“false”则表示结果失败，否则则表示成功
			results.setSuccessful(false);

		}else {
			results.setSuccessful(true);
		}

		return results;

	}

	/*
	 * 结束方法，实际运行时每个线程仅执行一次，在测试方法运行结束后执行
	 * 类似于LoadRunner中的end方法*/
	public void teardownTest(JavaSamplerContext arg0) {

	}

	/*main函数用于本地调试，打包时要注释掉*/
	public static void main(String[] args) {
		Arguments params = new Arguments();
		params.addArgument("testStr","51zxw");   //设置参数，并赋予默认值1
		JavaSamplerContext arg0 = new JavaSamplerContext(params);
		jmeter_test test = new jmeter_test();

		test.setupTest(arg0);
		test.runTest(arg0);
		test.teardownTest(arg0);
	}

}
