package com.eric.caffeinedemo.service.configCompare.parse.impl;

import com.eric.caffeinedemo.service.configCompare.parse.Parser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * <p>
 * properties文件解析器
 * </p>
 *
 * @author Eric Tseng
 * @since 2022-12-03
 */
public class PropertiesFileParser implements Parser {

    private static final String PROPERTIES = ".properties";

    @Override
    public Map<String, Object> parse(File file) {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(file));
        } catch (IOException e) {
            throw new RuntimeException("Load properties failed!", e);
        }
        return new HashMap<String, Object>((Map) properties);
    }

    @Override
    public boolean supports(File file) {
        if (file == null) {
            return false;
        }

        String fileName = file.getName();
        return fileName.endsWith(PROPERTIES);
    }

}
