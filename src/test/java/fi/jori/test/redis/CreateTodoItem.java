package fi.jori.test.redis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fi.jori.test.redis.dao.TodoDao;
import fi.jori.test.redis.entity.TodoItem;


public class CreateTodoItem {

	@Test
	public void createItem() {
		
		String text = "lorem ipsum";
		String email = "jori@foo.bar";
		
		TodoDao todoDao = new TodoDao();
		TodoItem item = todoDao.createTodoItem(email, text);
		
		assertNotNull(item);
		assertNotNull(item.getUid());
		assertNotNull(item.getCreated());
		assertTrue(System.currentTimeMillis() >= item.getCreated().getTime());
		assertEquals(email, item.getEmail());
		assertEquals(text, item.getText());
	}
	
}
