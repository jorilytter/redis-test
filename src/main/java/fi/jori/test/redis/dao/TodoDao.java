package fi.jori.test.redis.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import fi.jori.test.redis.entity.TodoItem;

public class TodoDao extends GenericDao {
	
	private static final String FIELD_TEXT = "text";
	private static final String FIELD_CREATED = "created";
	private static final String COLON = ":";
	private static final String PREFIX = "todo:";
	
	public TodoItem createTodoItem(String email, String text) {
		
		String uid = UUID.randomUUID().toString();
		String key = PREFIX+email+COLON+uid;
		String time = currentTime();
		
		insert(key, FIELD_CREATED, time);
		insert(key, FIELD_TEXT, text);
		
		Map<String, String> data = fetchMap(key);
		Long fetchedTime = Long.parseLong(data.get(FIELD_CREATED));
		TodoItem item = initTodoItem(email, uid, data.get(FIELD_TEXT), fetchedTime);
		
		return item;
	}

	private TodoItem initTodoItem(String email, String uid, String text, Long fetchedTime) {

		TodoItem item = new TodoItem();
		item.setUid(uid);
		item.setEmail(email);
		item.setCreated(new Date(fetchedTime));
		item.setText(text);
		return item;
	}

	private String currentTime() {

		long timeMs = new Date().getTime();
		String time = String.valueOf(timeMs);
		return time;
	}

	public List<TodoItem> findTodoItemsByEmail(String email) {

		Set<String> keys = findKeys(PREFIX+email);
		List<TodoItem> items = new ArrayList<TodoItem>();
		
		for(String key : keys) {
			
			Map<String, String> data = fetchMap(key);
			String uid = extractUid(key);
			Long fetchedTime = Long.parseLong(data.get(FIELD_CREATED));
			
			TodoItem item = initTodoItem(email, uid, data.get(FIELD_TEXT), fetchedTime);
			items.add(item);
		}
		return items;
	}

	private String extractUid(String key) {

		int index = key.lastIndexOf(COLON)+1;
		String uid = key.substring(index);
		return uid;
	}

}
