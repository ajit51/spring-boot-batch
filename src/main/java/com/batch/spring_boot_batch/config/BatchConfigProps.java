package com.batch.spring_boot_batch.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BatchConfigProps {

    @Autowired
    private Environment env;

    Map<String, String> configProp;

    public BatchConfigProps() {
        this.configProp = new HashMap<>();
    }

    public Map<String, String> getConfigProp() {
        return configProp;
    }

    public void setConfigProp(Map<String, String> configProp) {
        this.configProp = configProp;
    }

    public void addConfigProp(String key, String value) {
        configProp.put(key, value);
    }
}
