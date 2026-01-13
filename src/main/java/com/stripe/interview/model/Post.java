package com.stripe.interview.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import lombok.Data;

import java.io.FileWriter;
import java.io.Writer;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)  // Ignore any unknown fields
public class Post {
    private String title;
    private String body;
    private Integer userId;
//    private Integer id;

    public void writePostsToCsv(Post posts, String filePath) throws Exception {
        try (Writer writer = new FileWriter(filePath)) {
            StatefulBeanToCsv<Post> beanToCsv = new StatefulBeanToCsvBuilder<Post>(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .build();

            beanToCsv.write(posts);
        }
    }
}