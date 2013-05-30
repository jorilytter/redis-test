package fi.jori.test.redis.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import fi.jori.test.redis.entity.TodoItem;

public class TodoDao extends GenericDao {
	
	private static final String PREFIX = "todo:";
	
	public TodoItem createTodoItem(String email, String text) {
		
		String uid = UUID.randomUUID().toString();
		
		String key = PREFIX+email+":"+uid;
		
		long timeMs = new Date().getTime();
		String time = String.valueOf(timeMs);
		
		insert(key, "created", time);
		insert(key, "text", text);
		
		Map<String, String> data = fetch(key);
		
		Long fetchedTime = Long.parseLong(data.get("created"));
		
		TodoItem item = new TodoItem();
		item.setUid(uid);
		item.setEmail(email);
		item.setCreated(new Date(fetchedTime));
		item.setText(data.get("text"));
		
		return item;
	}

	public List<TodoItem> findTodoItemsByEmail(String email) {

		Set<String> keys = findKeys(PREFIX+email);
		List<TodoItem> items = new ArrayList<TodoItem>();
		
		for(String key : keys) {
			
			Map<String, String> data = fetch(key);
			int index = key.lastIndexOf(":")+1;
			String uid = key.substring(index);
			Long fetchedTime = Long.parseLong(data.get("created"));
			
			TodoItem item = new TodoItem();
			item.setUid(uid);
			item.setEmail(email);
			item.setCreated(new Date(fetchedTime));
			item.setText(data.get("text"));
			
			items.add(item);
		}
		return items;
	}

}
