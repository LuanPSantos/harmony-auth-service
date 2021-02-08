package com.harmony.authservice.domain.userregistration.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.harmony.authservice.common.exception.model.ClientException;
import com.harmony.authservice.common.exception.response.ExceptionResponse;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;


public class UserRegistrationErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder errorDecoder = new Default();


    @Override
    public Exception decode(String methodKey, Response response) {
        Reader reader = null;

        try {
            reader = response.body().asReader(StandardCharsets.UTF_8);
            String result = IOUtils.toString(reader);

            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(FAIL_ON_UNKNOWN_PROPERTIES);

            ExceptionResponse exceptionResponse = mapper.readValue(result, ExceptionResponse.class);

            return new ClientException(exceptionResponse.getErrors());

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return errorDecoder.decode(methodKey, response);
    }
}
