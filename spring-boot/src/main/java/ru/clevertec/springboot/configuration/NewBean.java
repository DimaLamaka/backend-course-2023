package ru.clevertec.springboot.configuration;

import lombok.Data;

@Data
@NewBeanAnnotation
public class NewBean {
    private String name = "name";
}
