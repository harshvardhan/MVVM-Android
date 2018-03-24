package hellofresh.test.com.hellofresh;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Observer;
import android.arch.persistence.room.Room;
import android.content.Context;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.google.gson.Gson;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import hellofresh.test.com.hellofresh.db.HelloFreshDb;
import hellofresh.test.com.hellofresh.db.RecipeDao;
import hellofresh.test.com.hellofresh.util.TestUtil;
import hellofresh.test.com.hellofresh.vo.Recipe;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class RecipeDaoTest {
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    private HelloFreshDb database;
    private RecipeDao dao;
    private Recipe recipe;

    @Mock
    private Observer<List<Recipe>> observer;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        Gson gson = new Gson();

        Context context = InstrumentationRegistry.getTargetContext();
        database = Room.inMemoryDatabaseBuilder(context, HelloFreshDb.class)
                .allowMainThreadQueries().build();
        dao = database.recipeDao();
        recipe = TestUtil.createRecipe();
    }

    @After
    public void tearDown() throws Exception {
        database.close();
    }

    @Test
    public void insert() throws Exception {
        // given
        dao.getRecipies().observeForever(observer);
        // when
        dao.insert(recipe);
        // then
        verify(observer).onChanged(Collections.singletonList(recipe));
    }
}