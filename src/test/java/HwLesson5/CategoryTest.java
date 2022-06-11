package HwLesson5;

import HWLesson5.RetrofitUtils;
import HWLesson5.api.CategoryService;
import HWLesson5.dto.GetCategoryResponse;
import lombok.SneakyThrows;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CategoryTest {

    static CategoryService categoryService;
    @BeforeAll
    static void beforeAll() {
        categoryService = RetrofitUtils.getRetrofit().create(CategoryService.class);
    }

    @SneakyThrows
    @Test
    void getCategoryByIdPositiveTest() {
        Response<GetCategoryResponse> response = categoryService.getCategory(1).execute();

        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.code(), Matchers.is(200));
        assertThat(response.body().getId(), equalTo(1));
        assertThat(response.body().getTitle(), equalTo("Food"));



    }
    @SneakyThrows
    @Test
    void getCategoryByIdNegativeTest() {
        Response<GetCategoryResponse> response = categoryService.getCategory(20).execute();

        assertThat(response.isSuccessful(), CoreMatchers.is(false));
        assertThat(response.code(), Matchers.is(404));


    }
}
