package com.jeecms.core.template;

import com.jeecms.common.developer.ModuleGenerator;

public class CoreModuleGenerator {
	private static String packName = "com.jeecms.core.template";
	private static String fileName = "jeecore.properties";

	public static void main(String[] args) {
		new ModuleGenerator(packName, fileName).generate();
	}
}
