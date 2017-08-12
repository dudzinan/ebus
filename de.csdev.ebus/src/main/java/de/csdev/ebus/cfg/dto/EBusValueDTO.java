package de.csdev.ebus.cfg.dto;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EBusValueDTO {

    private final Logger logger = LoggerFactory.getLogger(EBusValueDTO.class);

    private List<EBusValueDTO> children;
    private BigDecimal factor;
    private String label;
    private Integer length;
    private Map<String, String> mapping;
    private BigDecimal max;
    private BigDecimal min;
    private String name;
    private BigDecimal replaceValue;
    private BigDecimal step;
    private String type;
    private String xdefault;
    private Integer pos;

    public Integer getPos() {
        return pos;
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }

    public List<EBusValueDTO> getChildren() {
        return children;
    }

    public String getDefault() {
        return xdefault;
    }

    public BigDecimal getFactor() {
        return factor;
    }

    public String getLabel() {
        return label;
    }

    public Integer getLength() {
        return length;
    }

    public Map<String, String> getMapping() {
        return mapping;
    }

    public BigDecimal getMax() {
        return max;
    }

    public BigDecimal getMin() {
        return min;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getReplaceValue() {
        return replaceValue;
    }

    public BigDecimal getStep() {
        return step;
    }

    public String getType() {
        return type;
    }

    public void setChildren(List<EBusValueDTO> children) {
        this.children = children;
    }

    public void setDefault(String xdefault) {
        this.xdefault = xdefault;
    }

    public void setFactor(BigDecimal factor) {
        this.factor = factor;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public void setMapping(Map<String, String> mapping) {
        this.mapping = mapping;
    }

    public void setMax(BigDecimal max) {
        this.max = max;
    }

    public void setMin(BigDecimal min) {
        this.min = min;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReplaceValue(BigDecimal replaceValue) {
        this.replaceValue = replaceValue;
    }

    public void setStep(BigDecimal step) {
        this.step = step;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Object> getAsMap() {

        HashMap<String, Object> map = new HashMap<String, Object>();
        for (Field field : getClass().getDeclaredFields()) {
            try {
                Object value = field.get(this);

                // skip logger and empty values
                if (!field.getName().equals("logger") && value != null) {
                    map.put(field.getName(), value);
                }

            } catch (IllegalArgumentException e) {
                logger.error("error!", e);

            } catch (IllegalAccessException e) {
                logger.error("error!", e);
            }
        }

        return map;
    }
}