package org.example.converter;

import org.openapitools.codegen.DefaultGenerator;
import org.openapitools.codegen.config.CodegenConfigurator;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class OpenApiToAsciidoc {

    public static void main(String[] args) {
        OpenApiToAsciidoc asciidoc = new OpenApiToAsciidoc();
        asciidoc.jsonToAsciidoc("D:/workspaces/swagger-converter/documents/", "api-docs.json");
    }

    public void jsonToAsciidoc(String swaggerPath, String json) {

        System.out.println("Converting OpenAPI JSON to AsciiDoc...");

        // Input OpenAPI JSON file path
        String inputFile = swaggerPath + File.separator + json;

        // Output AsciiDoc file path
        String outputFile = swaggerPath + File.separator + "asciidoc";

        Map<String, Object> options = new HashMap<String, Object>();

        options.put("openapi", "3.0.1");
        options.put("dateLibrary", "java21");
        options.put("enumPropertyNaming", "UPPERCASE");
        options.put("hideGenerationTimestamp", true);
        options.put("version","1.0.0");
        options.put("version-label","1.0.0");
        options.put("license-id","Apache-2.0");
        options.put("developerEmail","admin@example.com");

        // Configure CodegenConfigurator
        CodegenConfigurator configurator = new CodegenConfigurator()
                .setInputSpec(inputFile)
                .setOutputDir(outputFile)
                .setGeneratorName("asciidoc")
                .setSkipOperationExample(true)
                .setAdditionalProperties(options)
                ;

        // Generate AsciiDoc file
        DefaultGenerator generator = new DefaultGenerator();
        generator.opts(configurator.toClientOptInput());
        generator.generate();


        System.out.println("Conversion completed. AsciiDoc file generated at: " + outputFile);


    }
}
