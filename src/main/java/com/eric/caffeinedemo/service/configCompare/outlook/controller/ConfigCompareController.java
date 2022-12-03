package com.eric.caffeinedemo.service.configCompare.outlook.controller;

import com.eric.caffeinedemo.service.configCompare.comparator.Action;
import com.eric.caffeinedemo.service.configCompare.comparator.CompareResult;
import com.eric.caffeinedemo.service.configCompare.comparator.ConfigComparator;
import com.eric.caffeinedemo.service.configCompare.comparator.DefaultMapComparator;
import com.eric.caffeinedemo.service.configCompare.parse.impl.YamlFileParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.net.URL;

/**
 * @author Eric Tseng
 * @description ConfigCompareController
 * @since 2022/12/3 16:29
 */
@RestController
@RequestMapping("/config")
public class ConfigCompareController {
    @GetMapping("/test")
    public Object parseAndCompareConfigFiles(String path1, String path2, String actionType) {
        CompareResult result = run(path1, path2, actionType);
        return result;
    }


    public static CompareResult run(String path1, String path2, String actionType) {
//        if (args.length < 2) {
//            System.out.println("[Usage]To use config-compare you should boot app like this 'java -jar config-compare.jar <oldFile> <newFile> [<action>]' .\n" +
//                    "The args you should pass follow the rule:\n" +
//                    "1. path to old file\n" +
//                    "2. path to new file\n" +
//                    "3. optional: action, eg 'diff-key' to compare key or 'diff-value' to compare value (default: diff-key)");
//            System.exit(1);
//        }
        File oldFile = tryToGetFile(path1);
        File newFile = tryToGetFile(path2);

        String action = Action.DIFF_KEY.getValue();
//        if (args.length == 3) {
            action = actionType;
            if (!Action.supports(action)) {
                System.out.println("Incorrect action, please specific 'diff-key' to compare key or 'diff-value' to compare value (default: key)");
//                System.exit(1);
            }
//        }
        if (!oldFile.exists()) {
            System.out.println("Incorrect path to old file!");
//            System.exit(1);
        }
        if (!newFile.exists()) {
            System.out.println("Incorrect path to old file!");
//            System.exit(1);
        }

        // 初始化比较器
        ConfigComparator configComparator = new ConfigComparator(oldFile, newFile, action, new DefaultMapComparator());

        // 注册解析器
        configComparator.registerParser(new YamlFileParser());
//        configComparator.registerParser(new PropertiesFileParser());
//        configComparator.registerParser(new JsonFileParser());

        // 启动比较器
        CompareResult compareResult = configComparator.startUp();
        return compareResult;
    }

    private static File tryToGetFile(String filePath) {
        // 先尝试从classpath加载
        try {
            URL resource = ConfigCompareController.class.getClassLoader().getResource(filePath);
            if (resource != null) {
                filePath = resource.getPath();
            }
        } catch (Exception e) {
            // ignore
        }
        return new File(filePath);
    }
}
