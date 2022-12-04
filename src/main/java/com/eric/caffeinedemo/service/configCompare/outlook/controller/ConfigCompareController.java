package com.eric.caffeinedemo.service.configCompare.outlook.controller;

import com.eric.caffeinedemo.service.configCompare.comparator.Action;
import com.eric.caffeinedemo.service.configCompare.comparator.CompareResult;
import com.eric.caffeinedemo.service.configCompare.comparator.ConfigComparator;
import com.eric.caffeinedemo.service.configCompare.comparator.DefaultMapComparator;
import com.eric.caffeinedemo.service.configCompare.parse.impl.YamlFileParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
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

    @RequestMapping(value = "/test1")
    public Object fileUpload(MultipartFile[] files, String actionType, HttpServletRequest request) throws Exception {
        return runFile(files[0], files[1], actionType);
    }

    public final static File transferToFile(MultipartFile multipartFile) {
        //选择用缓冲区来实现这个转换即使用java 创建的临时文件 使用 MultipartFile.transferto()方法 。
        File file = null;
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            //获取文件后缀
            assert originalFilename != null;
            String prefix = originalFilename.substring(originalFilename.lastIndexOf("."));
            // String[] filename = originalFilename.split("\\.");
            // file = File.createTempFile(filename[0], filename[1]);    //注意下面的 特别注意！！！
            file = File.createTempFile(originalFilename, prefix);    //创建临时文件
            multipartFile.transferTo(file);
            //删除
            file.deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static CompareResult runFile(MultipartFile file1, MultipartFile file2, String actionType) {
        String action = actionType;
        File oldFile = transferToFile(file1);
        File newFile = transferToFile(file2);
        if (!Action.supports(action)) {
            System.out.println("Incorrect action, please specific 'diff-key' to compare key or 'diff-value' to compare value (default: key)");
        }
        if (!oldFile.exists()) {
            System.out.println("Incorrect path to old file!");
        }
        if (!newFile.exists()) {
            System.out.println("Incorrect path to old file!");
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
