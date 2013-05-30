package fi.jori.test.redis;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import fi.jori.test.redis.dao.TodoDao;
import fi.jori.test.redis.entity.TodoItem;


public class FindTodoItemsByEmail {

	@Test
	public void find() {
		
		String email = "jori@foo.bar";
		
		TodoDao dao = new TodoDao();
		List<TodoItem> items = dao.findTodoItemsByEmail(email);
		
		assertNotNull(items);
		
		for(TodoItem item : items) {
			System.out.println(item.toString());
		}
	}
}
