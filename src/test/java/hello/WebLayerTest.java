package hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(HomeController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class WebLayerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void patchAlsoHasEmptyHttpieOrCurlOutput() throws Exception {
        this.mockMvc.perform(
                patch("/{name}", "World")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("greeting", "Hello")
        )
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello World")))
                .andDo(document("patch-with-path-variable",
                        pathParameters(
                                parameterWithName("name").description("the name")
                        ),
                        requestParameters(
                                parameterWithName("greeting").description("the greeting word")
                        )
                ));
    }

    @Test
    public void putHasHttpieAndCurlOutput() throws Exception {
        this.mockMvc.perform(
                put("/{name}", "World")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("greeting", "Hello")
        )
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello World")))
                .andDo(document("put-with-path-variable",
                        pathParameters(
                                parameterWithName("name").description("the name")
                        ),
                        requestParameters(
                                parameterWithName("greeting").description("the greeting word")
                        )
                ));
    }

    @Test
    public void patchWithoutPathVariable() throws Exception {
        this.mockMvc.perform(
                patch("/tony")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("greeting", "Hello")
        )
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello Tony")))
                .andDo(document("patch-without-path-variable",
                        requestParameters(
                                parameterWithName("greeting").description("the greeting word")
                        )
                ));
    }

    @Test
    public void putWithoutPathVariable() throws Exception {
        this.mockMvc.perform(
                put("/tony")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("greeting", "Hello")
        )
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello Tony")))
                .andDo(document("put-without-path-variable",
                        requestParameters(
                                parameterWithName("greeting").description("the greeting word")
                        )
                ));
    }

}
