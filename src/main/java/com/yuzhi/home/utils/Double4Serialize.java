package com.yuzhi.home.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.DecimalFormat;

public class Double4Serialize extends JsonSerializer<Double> {

    private DecimalFormat df = new DecimalFormat("0.0000");

    public static void main(String[] args) {
        System.out.println(new Double4Serialize().getClass().getName());
    }

    @Override
    public void serialize(Double arg0, JsonGenerator arg1, SerializerProvider arg2) throws IOException {
        if(arg0 != null) {
            arg1.writeString(df.format(arg0));
        }
    }
}
